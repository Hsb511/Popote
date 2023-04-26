package com.example.presentation.recipe.mappers

import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.math.roundToInt

class QuantityMapper @Inject constructor() {
	private val format2Digits = DecimalFormat("#.##").apply { roundingMode = RoundingMode.DOWN }
	private val format1Digit = DecimalFormat("#.#").apply { roundingMode = RoundingMode.DOWN }

	fun toString(quantity: Float): String = when {
		quantity < 10f -> format2Digits.format(quantity)
		quantity < 100f -> format1Digit.format(quantity)
		else -> quantity.roundToInt().toString()
	}
}
