package com.team23.neuracrsrecipes.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.create
import platform.Foundation.dataWithContentsOfURL
import platform.Foundation.stringByRemovingPercentEncoding
import platform.posix.memcpy
import org.jetbrains.skia.Image as SkiaImage

@Composable
actual fun getImageBitmapFromUri(uri: String): ImageBitmap? {
    // 1) data: URI
    if (uri.startsWith("data:", ignoreCase = true)) {
        return null
        /*val bytes = decodeDataUriToBytes(uri) ?: return null
        return runCatching {
            SkiaImage.makeFromEncoded(bytes).toComposeImageBitmap()
        }.getOrNull()*/
    }

    // 2) file://... or plain path -> load NSData
    val url: NSURL? = when {
        uri.startsWith("file://", ignoreCase = true) -> NSURL.URLWithString(uri)
        else -> NSURL.fileURLWithPath(uri) // allow raw paths too
    }

    val data: NSData = url?.let { NSData.dataWithContentsOfURL(it) } ?: return null
    val bytes = nsDataToByteArray(data)
    return runCatching {
        SkiaImage.makeFromEncoded(bytes).toComposeImageBitmap()
    }.getOrNull()
}

/* -------------------- helpers -------------------- */

@OptIn(ExperimentalForeignApi::class)
private fun nsDataToByteArray(data: NSData): ByteArray {
    val len = data.length.toInt()
    if (len == 0) return ByteArray(0)
    val out = ByteArray(len)
    out.usePinned { pinned ->
        memcpy(pinned.addressOf(0), data.bytes, data.length)
    }
    return out
}

/*
private fun decodeDataUriToBytes(dataUri: String): ByteArray? {
    // Format: data:<mime>;base64,<payload>
    val comma = dataUri.indexOf(',')
    if (comma <= 0) return null
    val header = dataUri.substring(0, comma)
    val payload = dataUri.substring(comma + 1)

    val isBase64 = header.contains(";base64", ignoreCase = true)
    return if (isBase64) {
        // Foundation base64 decode
        val nsString = payload as NSString
        val nsData = NSData.create(
            base64EncodedString = nsString,
            options = 0u
        )
        nsData?.let { nsDataToByteArray(it) }
    } else {
        // URL-encoded (rare for images) – decode percent-escapes
        val decoded = payload.stringByRemovingPercentEncoding() ?: return null
        decoded.encodeToByteArray()
    }
}*/