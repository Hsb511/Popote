package com.team23.view.extension

import androidx.compose.ui.text.intl.Locale
import com.team23.view.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

suspend fun getStringResource(id: String): String = readStringsResourceBytes().decodeXML(id)

suspend fun getStringResource(id: String, vararg stringValues: String): String =
    getStringResource(id).fillPlaceholdersWithValues(stringValues.toList())

@OptIn(ExperimentalResourceApi::class)
private suspend fun readStringsResourceBytes(): ByteArray = when (Locale.current.language) {
    "fr" -> Res.readBytes("values-fr/strings.commonMain.cvr")
    else -> Res.readBytes("values/strings.commonMain.cvr")
}

@OptIn(ExperimentalEncodingApi::class)
private fun ByteArray.decodeXML(id: String): String {
    try {
        return decodeToString()
            .split("\n")
            .firstOrNull { it.contains(id) }
            ?.split("|")
            ?.last()
            ?.let { Base64.decode(it).decodeToString() }
            .let { requireNotNull(it) }
    } catch (e: Exception) {
        throw IllegalArgumentException("No strings found for id $id")
    }
}

private fun String.fillPlaceholdersWithValues(stringValues: List<String>): String =
    this.split("%s")
        .zip(stringValues + "") { a, b -> "$a$b" }
        .joinToString(separator = "")
