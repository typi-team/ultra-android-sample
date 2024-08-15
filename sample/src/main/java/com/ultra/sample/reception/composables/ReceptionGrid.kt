package com.ultra.sample.reception.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReceptionGrid(
    onItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val receptionList = remember { (1..100).toList() }
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 5),
        modifier = modifier,
    ) {
        items(receptionList) { item ->
            ReceptionItem(
                item = item,
                onItemClicked = onItemClicked,
            )
        }
    }
}

@Composable
fun ReceptionItem(
    item: Int,
    onItemClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(40.dp)
            .background(Color.White)
            .clickable {
                onItemClicked(item.toString())
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(text = item.toString())
    }
}