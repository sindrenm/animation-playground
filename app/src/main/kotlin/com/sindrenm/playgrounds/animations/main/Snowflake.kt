package com.sindrenm.playgrounds.animations.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

// TODO: Are there perf benefits to data class `copy` vs. mutable state?
//  I opted for a mutable class here in order to recycle snowflakes,
//  but I'm not sure if it matters in the end.
class Snowflake(x: Float, y: Float) {
  val radius: Float = Random.nextDouble(from = 5.0, until = 10.0).toFloat()

  // TODO: Does it make sense to just set speed == radius here?
  //  Big ones fall faster, small ones fall slower?
  val speed: Float = Random.nextDouble(from = 5.0, until = 10.0).toFloat()

  val color: Color = Color.White

  // TODO: Instead of each snowflake having its own drift, should we have
  //  a global wind? That might make more sense in real life, but maybe be
  //  less interesting to look at.
  val drift: Float = Random.Default.nextDouble(-0.5, 0.5).toFloat()

  var x: Float by mutableFloatStateOf(x)
  var y: Float by mutableFloatStateOf(y)
}
