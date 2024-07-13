package com.thread_jetpack.itemView

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ThreadItem(){
    ConstraintLayout(modifier = Modifier.fillMaxWidth().padding(16.dp )) {
        val (userImage, userName, date, time, image) = createRefs()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview(){
    ThreadItem()
}