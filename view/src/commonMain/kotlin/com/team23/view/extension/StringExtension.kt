package com.team23.view.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun stringResource(id: String): String {
    var bytes by remember { mutableStateOf(ByteArray(0)) }
    LaunchedEffect(Unit) {
        bytes = resource("values/strings.xml").readBytes()
    }
    try {
        return bytes.decodeToString()
            .ifEmpty { return "" }
            .split("<string")
            .associate {
                val splitString = it.split("\"")
                splitString[1] to splitString[2]
            }[id]!!
            .split(">")[1]
            .split("<")[0]
    } catch (e: Exception) {
        throw IllegalArgumentException("No strings found for id $id")
    }
}
