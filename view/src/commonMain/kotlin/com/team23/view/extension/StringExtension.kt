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

@Composable
internal fun stringResource(id: String): String {
    var bytes by remember { mutableStateOf(ByteArray(0)) }
    LaunchedEffect(Unit) {
        bytes = readStringsResourceBytes()
    }
    return bytes.decodeXML(id)
}

@Composable
internal fun stringResource(id: String, vararg stringValues: String): String =
    stringResource(id).fillPlaceholdersWithValues(stringValues.toList())

suspend fun getStringResource(id: String): String = readStringsResourceBytes().decodeXML(id)

suspend fun getStringResource(id: String, vararg stringValues: String): String =
    getStringResource(id).fillPlaceholdersWithValues(stringValues.toList())

@OptIn(ExperimentalResourceApi::class)
private suspend fun readStringsResourceBytes(): ByteArray = when (Locale.current.language) {
    "fr" -> resource("values-fr/strings.xml")
    else -> resource("values/strings.xml")
}.readBytes()

private fun ByteArray.decodeXML(id: String): String {
    try {
        return decodeToString()
            .ifEmpty { return "" }
            .split("<string")
            .associate {
                val splitString = it.split("\"")
                splitString[1] to splitString[2]
            }[id]!!
            .split(">")[1]
            .split("<")[0]
            .replace("\\'", "'")
    } catch (e: Exception) {
        throw IllegalArgumentException("No strings found for id $id")
    }
}

private fun String.fillPlaceholdersWithValues(stringValues: List<String>) = this
    .split("%s")
    .mapIndexed { index, c ->
        if (index % 2 == 0) {
            c
        } else {
            stringValues.getOrNull((index - 1) / 2) ?: ""
        }
    }.joinToString(separator = "")
