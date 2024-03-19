package com.ultra.sample.money.data

import com.typi.ultra.integration.cache.UltraCacheProvider
import com.typi.ultra.transaction.model.Money
import com.typi.ultra.transaction.model.Transaction
import com.typi.ultra.transaction.model.TransactionStatus
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

interface MoneyRepository {

    suspend fun sendMoney(money: Money)
}

class MoneyRepositoryImpl(
    private val cacheProvider: UltraCacheProvider,
) : MoneyRepository {

    override suspend fun sendMoney(money: Money) {
        withContext(Dispatchers.IO) {
            runCatching {
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
                    jsonParam.put("amount", money.units)
                    jsonParam.put("currency", money.currencyCode)
                    Timber.tag("MONEY-MOCK").d("REQUEST: $jsonParam")

                    os.writeBytes(jsonParam.toString())
                }

                if (connection.responseCode < 200 || connection.responseCode >= 300) {
                    Timber.d("responseCode: ${connection.responseCode}")
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
                Timber.d("RESPONSE: $json")
                val jsonResponse = JSONObject(json)
                val transactionId = jsonResponse.getString("transaction_id")
                val status = when (jsonResponse.getString("status")) {
                    "in_progress" -> TransactionStatus.IN_PROGRESS.value
                    "completed" -> TransactionStatus.COMPLETED.value
                    "rejected" -> TransactionStatus.REJECTED.value
                    else -> TransactionStatus.MONEY_STATUS_UNKNOWN.value
                }
                val transaction = Transaction(
                    transactionId = transactionId,
                    money = money,
                    status = status
                )
                cacheProvider.saveTransaction(transaction)
            }.onFailure { Timber.e(it, "Couldn't send money") }
        }
    }
}