package com.sindrenm.playgrounds.animations.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sindrenm.playgrounds.animations.core.theme.AnimationsPlaygroundTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun MainScreen() {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text("Animations Playground")
        },
      )
    },
  ) { contentPadding ->
    Box(Modifier.padding(contentPadding))
  }
}

@Preview
@Composable
private fun MainScreenPreview() {
  AnimationsPlaygroundTheme {
    MainScreen()
  }
}
