package com.team23.view

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

val LocalScrollState: ProvidableCompositionLocal<ScrollState> =
    compositionLocalOf { ScrollState(initial = 0) }

val LocalHeightToBeFaded: ProvidableCompositionLocal<MutableState<Float>> =
    compositionLocalOf { mutableStateOf(0f) }

val LocalTitle: ProvidableCompositionLocal<MutableState<String?>> =
    compositionLocalOf { mutableStateOf(null) }
