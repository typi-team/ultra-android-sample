package com.ultra.sample.money.data

import com.typi.ultra.integration.cache.UltraCacheProvider
import com.typi.ultra.integration.transaction.UltraTransaction
import com.typi.ultra.transaction.model.TransactionStatus
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

interface MoneyRepository {

    suspend fun sendMoney(amount: Float)
}

class MoneyRepositoryImpl(
    private val cacheProvider: UltraCacheProvider,
) : MoneyRepository {

    override suspend fun sendMoney(amount: Float) {
        withContext(Dispatchers.IO) {
            runCatching {
                val currency = "KZT"
                val url = URL("https://ultra-dev.typi.team/mock/v1/transfer")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.doOutput = true
                connection.doInput = true
                connection.connectTimeout = 5000
                connection.readTimeout = 5000

                DataOutputStream(connection.outputStream).use { os ->
                    val jsonParam = JSONObject()
                    jsonParam.put("sender", "foo")
                    jsonParam.put("receiver", "bar")
                    jsonParam.put("amount", amount)
                    jsonParam.put("currency", currency)
                    Timber.tag("MONEY-MOCK").d("REQUEST: $jsonParam")

                    os.writeBytes(jsonParam.toString())
                }

                if (connection.responseCode < 200 || connection.responseCode >= 300) {
                    Timber.tag("MONEY-MOCK").d("responseCode: ${connection.responseCode}")
                    throw IllegalStateException("Network error with response code: ${connection.responseCode}")
                }
                val json = BufferedReader(InputStreamReader(connection.inputStream)).use { br ->
                    val sb = StringBuilder()
                    var line: String?
                    while (br.readLine().also { line = it } != null) {
                        sb.append(line + "\n");
                    }
                    sb.toString()
                }
                Timber.tag("MONEY-MOCK").d("RESPONSE: $json")
                val jsonResponse = JSONObject(json)
                val transactionId = jsonResponse.getString("transaction_id")
                val status = when (jsonResponse.getString("status")) {
                    "in_progress" -> TransactionStatus.IN_PROGRESS
                    "completed" -> TransactionStatus.COMPLETED
                    "rejected" -> TransactionStatus.REJECTED
                    else -> TransactionStatus.MONEY_STATUS_UNKNOWN
                }
                val transaction = UltraTransaction(
                    id = transactionId,
                    currency = currency,
                    amount = amount,
                    status = status,
                    statusDate = Date(),
                    reception = "",
                )
                cacheProvider.saveTransaction(transaction)
            }.onFailure { Timber.e(it, "Couldn't send money") }
        }
    }
}