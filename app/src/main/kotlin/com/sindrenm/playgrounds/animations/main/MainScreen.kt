package com.sindrenm.playgrounds.animations.main

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.LocalActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sindrenm.playgrounds.animations.core.theme.AnimationsPlaygroundTheme
import com.sindrenm.playgrounds.animations.main.animations.backgrounds.FloatingEmojis

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun MainScreen() {
  val activity = LocalActivity.current as? ComponentActivity

  LaunchedEffect(activity) {
    activity?.enableEdgeToEdge(
      statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
      navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
    )
  }

  Scaffold(
    containerColor = Color.Black,
    topBar = {
      TopAppBar(
        title = {
          Text("Animations Playground")
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color.Black.copy(alpha = .4f),
          scrolledContainerColor = Color.Black.copy(alpha = .4f),
          titleContentColor = Color.White,
          navigationIconContentColor = Color.White,
          actionIconContentColor = Color.White,
          subtitleContentColor = Color.White,
        ),
      )
    },
  ) { contentPadding ->
    Box(Modifier.fillMaxSize()) {
      FloatingEmojis(listOf("❄", "🌈", "💙", "🌴"), Modifier.fillMaxSize())

      ScreenContent(contentPadding)
    }
  }
}

@Preview
@Composable
private fun MainScreenPreview() {
  AnimationsPlaygroundTheme {
    MainScreen()
  }
}
