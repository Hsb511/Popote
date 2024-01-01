package com.team23.view.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.intl.Locale
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun stringResource(id: String): String {
    var bytes by remember { mutableStateOf(ByteArray(0)) }
    LaunchedEffect(Unit) {
        bytes = when(Locale.current.language) {
            "fr" -> resource("values-fr/strings.xml")
            else -> resource("values/strings.xml")
        }.readBytes()
    }
    try {
        return bytes.decodeToString()
            .also { println("HUGO - decodeToString $it") }
            .ifEmpty { return "" }
            .split("<string")
            .also { println("HUGO - split $it") }
            .associate {
                val splitString = it.split("\"")
                splitString[1] to splitString[2]
            }[id]!!

            .also { println("HUGO - associate get $it") }
            .split(">")[1]
            .split("<")[0]
            .replace("\\'", "'")
    } catch (e: Exception) {
        throw IllegalArgumentException("No strings found for id $id")
    }
}

@Composable
internal fun stringResource(id: String, vararg stringValues: String): String =
    stringResource(id)
        .split("%s")
        .mapIndexed { index, c ->
            c + stringValues.getOrNull(index)?.let { "" }
    }.joinToString(separator = "")
