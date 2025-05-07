package com.sindrenm.playgrounds.animations.main.animations.backgrounds

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
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
      Snowflake(
        x = Random.nextDouble(0.0, windowIntSize.width.toDouble()).toFloat(),
        y = Random.nextDouble(0.0, windowIntSize.height.toDouble()).toFloat(),
      )
    }
  }

  LaunchedEffect(windowIntSize) { // Or use a key that changes if you want to restart based on some param
    debug("windowIntSize: $windowIntSize")

    while (isActive) {
      snowflakes.forEachIndexed { index, snowflake ->
        var newY = snowflake.y + snowflake.speed
        var newX = snowflake.x + snowflake.drift

        debug("newY: $newY")
        debug("newX: $newX")

        // Recycle snowflake if it goes off screen
        if (newY > windowIntSize.height + snowflake.radius * 2) {
          newY = -snowflake.radius * 2 // Reset to top
          newX = Random.nextFloat() * windowIntSize.width // New random X

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

  Canvas(modifier) {
    snowflakes.forEach { snowflake ->
      drawSnowFlake(snowflake)
    }
  }
}

private fun DrawScope.drawSnowFlake(snowflake: Snowflake) {

  drawCircle(
    center = Offset(x = snowflake.x, y = snowflake.y,),
    color = snowflake.color,
    radius = snowflake.radius.dp.toPx(),
  )

  debug("Drawing snowflake at ${Offset(x = snowflake.x, y = snowflake.y,)}")
}
