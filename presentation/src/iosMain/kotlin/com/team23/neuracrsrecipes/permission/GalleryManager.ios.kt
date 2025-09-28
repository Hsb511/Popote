package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.team23.neuracrsrecipes.model.property.ImageProperty
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret
import platform.Foundation.NSData
import platform.Foundation.NSDate
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.timeIntervalSince1970
import platform.Foundation.writeToURL
import platform.PhotosUI.PHPickerConfiguration
import platform.PhotosUI.PHPickerFilter
import platform.PhotosUI.PHPickerResult
import platform.PhotosUI.PHPickerViewController
import platform.PhotosUI.PHPickerViewControllerDelegateProtocol
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.UIKit.UIWindowScene
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

// ---------- Composable ----------
@Composable
actual fun rememberGalleryManager(
    onResult: (ImageProperty.UserPick) -> Unit
): GalleryManager {
    val onResultState = rememberUpdatedState(onResult)

    return remember {
        GalleryManager(
            onLaunch = {
                val config = PHPickerConfiguration().apply {
                    selectionLimit = 1L
                    filter = PHPickerFilter.imagesFilter()
                }
                val picker = PHPickerViewController(configuration = config).apply {
                    delegate = GalleryPickerDelegate { pick ->
                        onResultState.value(pick)
                    }
                }
                TopViewControllerProvider().top()
                    ?.presentViewController(picker, animated = true, completion = null)
            }
        )
    }
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class GalleryManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}

@OptIn(ExperimentalForeignApi::class)
private class GalleryPickerDelegate(
    private val onPicked: (ImageProperty.UserPick) -> Unit
) : NSObject(), PHPickerViewControllerDelegateProtocol {

    private val utTypeImage = "public.image"

    override fun picker(
        picker: PHPickerViewController,
        didFinishPicking: List<*>
    ) {
        /*picker.dismissViewControllerAnimated(true, completion = null)
        val result = didFinishPicking.firstOrNull() as? PHPickerResult ?: return
        val provider = result.itemProvider

        if (provider.hasItemConformingToTypeIdentifier(utTypeImage)) {
            provider.loadDataRepresentationForTypeIdentifier(utTypeImage) { data, _ ->
                val nsData = data ?: return@loadDataRepresentationForTypeIdentifier
                val (uri, _) = writeImageDataToTempFile(nsData)
                dispatch_async(dispatch_get_main_queue()) {
                    onPicked(
                        ImageProperty.UserPick(
                            contentDescription = null,
                            uri = uri  // e.g., file:///var/mobile/Containers/Data/...
                        )
                    )
                }
            }*/
    }

    /*private fun writeImageDataToTempFile(data: NSData): Pair<String, String> {
        // Quick signature check: PNG vs JPEG
        val mime = detectMime(data)
        val ext = if (mime == "image/png") "png" else "jpg"
        val tmpDir = NSTemporaryDirectory() as String
        val filename = "gallery_${NSDate().timeIntervalSince1970}.$ext"
        val path = "$tmpDir/$filename"
        val url = NSURL.fileURLWithPath(path)
        data.writeToURL(url, atomically = true)
        return Pair(url.absoluteString ?: path, mime)
    }

    private fun detectMime(data: NSData): String {
        // PNG: 89 50 4E 47 ; JPEG: FF D8 FF
        if (data.length >= 4u && data.bytes != null) {
            val b0 = data.bytes!!.reinterpret<ByteVar>()[0].toUByte().toInt()
            val b1 = data.bytes!!.reinterpret<ByteVar>()[1].toUByte().toInt()
            val b2 = data.bytes!!.reinterpret<ByteVar>()[2].toUByte().toInt()
            val b3 = data.bytes!!.reinterpret<ByteVar>()[3].toUByte().toInt()
            return when {
                b0 == 0x89 && b1 == 0x50 && b2 == 0x4E && b3 == 0x47 -> "image/png"
                b0 == 0xFF && b1 == 0xD8 && b2 == 0xFF -> "image/jpeg"
                else -> "image/jpeg"
            }
        }
        return "image/jpeg"
    }*/
}
