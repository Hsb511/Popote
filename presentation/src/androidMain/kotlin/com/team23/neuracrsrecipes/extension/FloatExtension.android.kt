package com.team23.neuracrsrecipes.extension

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

actual fun Float.toReadableQuantity(): String = when {
    this < 10f -> format2Digits.format(this)
    this < 100f -> format1Digit.format(this)
    else -> this.roundToInt().toString()
}

private val format2Digits = DecimalFormat("#.##").apply { roundingMode = RoundingMode.DOWN }
private val format1Digit = DecimalFormat("#.#").apply { roundingMode = RoundingMode.DOWN }
