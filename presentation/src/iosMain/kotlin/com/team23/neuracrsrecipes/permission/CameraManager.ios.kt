package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import com.team23.neuracrsrecipes.model.property.ImageProperty
import kotlinx.cinterop.COpaquePointer
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
import platform.UIKit.UIImage
import platform.UIKit.UIViewController
import platform.UIKit.UIWindowScene
import platform.darwin.ByteVar
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

@Composable
actual fun rememberCameraManager(
    onResult: (ImageProperty.UserPick) -> Unit
): CameraManager {
    val onResultState = rememberUpdatedState(onResult)

    val manager = remember {
        CameraManager(
            onLaunch = {
                // When launch() is called, show the PHPicker
                val config = PHPickerConfiguration().apply {
                    selectionLimit = 1L
                    filter = PHPickerFilter.imagesFilter()
                }
                val picker = PHPickerViewController(configuration = config).apply {
                    delegate = PickerDelegate { imagePick ->
                        onResultState.value(imagePick)
                    }
                }
                TopViewControllerProvider().top()
                    ?.presentViewController(picker, animated = true, completion = null)
            }
        )
    }

    return manager
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit
) {
    private val pickerDelegate = PickerDelegate { onLaunch() }
    private val topProvider = TopViewControllerProvider()

    actual fun launch() {
        val config = PHPickerConfiguration().apply {
            selectionLimit = 1L
            filter = PHPickerFilter.imagesFilter()
        }
        val picker = PHPickerViewController(configuration = config).apply {
            delegate = pickerDelegate
        }
        topProvider.top()?.presentViewController(picker, animated = true, completion = null)
    }
}

@OptIn(ExperimentalForeignApi::class)
private class PickerDelegate(
    private val onPicked: (ImageProperty.UserPick) -> Unit
) : NSObject(), PHPickerViewControllerDelegateProtocol {

    // Uniform Type Identifier for images
    private val utTypeImage = "public.image"

    override fun picker(
        picker: PHPickerViewController,
        didFinishPicking: List<*>
    ) {
        /*picker.dismissViewControllerAnimated(true, completion = null)

        val result = didFinishPicking.firstOrNull() as? PHPickerResult ?: return
        val provider = result.itemProvider

        // Prefer data representation to avoid class bridging (no classForCoder needed)
        if (provider.hasItemConformingToTypeIdentifier(utTypeImage)) {
            provider.loadDataRepresentationForTypeIdentifier(utTypeImage) { data: NSData?, error: NSError? ->
                if (data == null) return@loadDataRepresentationForTypeIdentifier
                // write to a temp file and return a file:// URI
                val (uriString, mime) = writeImageDataToTempFile(data)
                dispatch_async(dispatch_get_main_queue()) {
                    onPicked(
                        ImageProperty.UserPick(
                            contentDescription = null,
                            uri = uriString, // e.g., file:///var/mobile/Containers/...
                        )
                    )
                }
            }
        }*/
    }

    /*private fun writeImageDataToTempFile(data: NSData): Pair<String, String> {
        // Try to detect JPEG vs PNG quickly from magic bytes
        val mime = when {
            (data.length > 3u) && (data.bytes?.let { ptr ->
                val b0 = ptr.readUByte()
                val b1 = (ptr + 1)!!.readUByte()
                val b2 = (ptr + 2)!!.readUByte()
                val b3 = (ptr + 3)!!.readUByte()
                // PNG = 89 50 4E 47, JPEG starts with FF D8 FF
                (b0.toInt() == 0x89 && b1.toInt() == 0x50 && b2.toInt() == 0x4E && b3.toInt() == 0x47)
                    || (b0.toInt() == 0xFF && b1.toInt() == 0xD8 && b2.toInt() == 0xFF)
            } == true) -> {
                // crude: if starts with PNG signature → png else → jpeg
                val b0 = data.bytes!!.readUByte()
                if (b0.toInt() == 0x89) "image/png" else "image/jpeg"
            }
            else -> "image/jpeg"
        }

        val ext = if (mime == "image/png") "png" else "jpg"
        val tmpDir = NSTemporaryDirectory() as String
        val filename = "pick_${NSDate().timeIntervalSince1970}.$ext"
        val path = tmpDir + "/" + filename
        val url = NSURL.fileURLWithPath(path)
        data.writeToURL(url, atomically = true)
        return Pair(url.absoluteString ?: path, mime)
    }*/

    // tiny helpers to peek a few bytes

    //private fun COpaquePointer.readUByte(): UByte = this.reinterpret<ByteVar>().pointed.value.toUByte()
}
