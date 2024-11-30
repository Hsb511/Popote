package com.team23.neuracrsrecipes.extension

import kotlin.math.roundToInt
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSRoundingMode


actual fun Float.toReadableQuantity(): String = when {
    this < 10f -> format2Digits.stringFromNumber(NSNumber(this))
    this < 100f -> format1Digit.stringFromNumber(NSNumber(this))
    else -> this.roundToInt().toString()
}.orEmpty()

private val format2Digits = NSNumberFormatter().apply {
    minimumFractionDigits = 2u
    maximumFractionDigits = 2u
    roundingMode = NSRoundingMode.NSRoundDown.value
}

private val format1Digit =  NSNumberFormatter().apply {
    minimumFractionDigits = 1u
    maximumFractionDigits = 1u
    roundingMode = NSRoundingMode.NSRoundDown.value
}
