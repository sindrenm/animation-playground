package com.sindrenm.playgrounds.animations.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import kotlin.random.Random

// TODO: Are there perf benefits to data class `copy` vs. mutable state?
//  I opted for a mutable class here in order to recycle snowflakes,
//  but I'm not sure if it matters in the end.
class Snowflake(x: Float, y: Float) {
  constructor(containerSize: IntSize) : this(
    x = Random.nextDouble(0.0, containerSize.width.toDouble()).toFloat(),
    y = Random.nextDouble(0.0, containerSize.height.toDouble()).toFloat(),
  )

  val speed: Float = Random.nextDouble(from = 5.0, until = 10.0).toFloat()
  val drift: Float = Random.Default.nextDouble(-1.0, 1.0).toFloat()

  var x: Float by mutableFloatStateOf(x)
  var y: Float by mutableFloatStateOf(y)
}
