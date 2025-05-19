package com.sindrenm.playgrounds.animations.main.animations.backgrounds

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun FloatingEmojis(
  emojis: List<String>,
  modifier: Modifier = Modifier,
) {
  val textMeasurer = rememberTextMeasurer()
  val textLayoutResults = emojis.map { textMeasurer.measure(it, style = LocalTextStyle.current.copy(fontSize = 56.sp)) }

  val emojiHeightPx = textLayoutResults.first().size.height

  val animatable = remember { Animatable(-50f) }

  LaunchedEffect(Unit) {
    animatable.animateTo(
      targetValue = 50f,
      animationSpec = infiniteRepeatable(
        animation = tween(1000, easing = EaseInOut),
        repeatMode = RepeatMode.Reverse,
      ),
    )
  }

  Canvas(modifier.fillMaxSize()) {
    val numberOfRows = size.height / (emojiHeightPx + 0)
    var currentEmojiIndex = 0

    repeat(numberOfRows.roundToInt()) { rowIndex ->
      val y = rowIndex * 300f + animatable.value

      if (rowIndex % 2 == 0) {
        val left = textLayoutResults[currentEmojiIndex++ % emojis.size]

        drawText(
          textLayoutResult = left,
          topLeft = Offset(x = 0f, y = y),
        )

        val right = textLayoutResults[currentEmojiIndex++ % emojis.size]

        drawText(
          textLayoutResult = right,
          topLeft = Offset(x = size.width - right.size.width, y = y),
        )
      } else {
        val layoutResult = textLayoutResults[currentEmojiIndex++ % emojis.size]

        drawText(
          textLayoutResult = layoutResult,
          topLeft = Offset(x = center.x - (layoutResult.size.width / 2), y = y),
        )
      }
    }
  }
}

@Preview
@Composable
private fun FloatingEmojisPreview() {
  MaterialTheme {
    Surface {
      FloatingEmojis(
        listOf("❄", "🌈", "💙", "🌴"),
        Modifier
          .fillMaxSize()
          .padding(42.dp),
      )
    }
  }
}
