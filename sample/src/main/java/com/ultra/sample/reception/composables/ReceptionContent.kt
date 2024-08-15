package com.ultra.sample.reception.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ultra.sample.R

@Composable
fun ReceptionContent(
    onChangeClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ReceptionInput(
            onChangeClicked = onChangeClicked,
            modifier = Modifier.padding(16.dp),
        )
        Text(
            text = stringResource(R.string.or_select_from_the_list),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ReceptionGrid(
            onItemClicked = onChangeClicked,
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
        )
    }
}