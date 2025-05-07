package com.sindrenm.playgrounds.animations.main

fun debug(message: String) {
  if (DEBUG) println("DEBUG :: $message")
}

private const val DEBUG = false
