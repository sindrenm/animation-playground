package com.sindrenm.playgrounds.animations.main.animations.backgrounds

import androidx.compose.foundation.Canvas
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import com.sindrenm.playgrounds.animations.main.Snowflake
import com.sindrenm.playgrounds.animations.main.debug
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.random.Random

@Composable
fun FallingSnow(modifier: Modifier = Modifier) {
  // TODO: Use size of actual container, not the whole window.
  val windowIntSize = LocalWindowInfo.current.containerSize

  val snowflakes = remember {
    List(50) {
      Snowflake(windowIntSize)
    }
  }

  val fontSize = 40.sp
  val fontSizePx = with(LocalDensity.current) { fontSize.roundToPx() }

  LaunchedEffect(windowIntSize) {
    debug("windowIntSize: $windowIntSize")

    while (isActive) {
      snowflakes.forEachIndexed { index, snowflake ->
        var newY = snowflake.y + snowflake.speed
        var newX = snowflake.x + snowflake.drift

        // Recycle snowflake if it goes off screen
        if (newY > windowIntSize.height + fontSizePx) {
          newY = -fontSizePx.toFloat()
          newX = Random.nextDouble(0.0, windowIntSize.width.toDouble()).toFloat()

          snowflake.x = newX
          snowflake.y = newY
        } else {
          snowflake.x = newX
          snowflake.y = newY
        }
      }

      val targetFrameRate = 60

      delay(1000L / targetFrameRate)
    }
  }

  val textMeasurer = rememberTextMeasurer()
  val baseTextStyle = LocalTextStyle.current
  val textLayoutResult = textMeasurer.measure("❄", style = baseTextStyle.copy(fontSize = fontSize))

  Canvas(modifier) {
    snowflakes.forEach { snowflake ->
      drawSnowFlake(snowflake, textLayoutResult)
    }
  }
}

private fun DrawScope.drawSnowFlake(snowflake: Snowflake, textLayoutResult: TextLayoutResult) {
  val radius = textLayoutResult.size / 2
  val topLeft = Offset(x = snowflake.x - radius.width, y = snowflake.y - radius.height)

  drawText(
    textLayoutResult = textLayoutResult,
    topLeft = topLeft,
  )

  debug("Drawing snowflake at ${Offset(x = snowflake.x, y = snowflake.y)}")
}
