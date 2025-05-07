package com.sindrenm.playgrounds.animations.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenContent(contentPadding: PaddingValues) {
  Box(
    Modifier
      .fillMaxSize()
      .padding(contentPadding)
      .padding(24.dp),
  ) {
    Card(
      Modifier
        .size(300.dp)
        .align(Alignment.Center),
    ) {
    }

    Card(
      Modifier
        .fillMaxWidth()
        .height(100.dp)
        .align(Alignment.BottomCenter),
    ) {
    }
  }
}
