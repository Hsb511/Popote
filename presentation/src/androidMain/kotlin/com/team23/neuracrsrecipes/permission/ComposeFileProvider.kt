package com.team23.neuracrsrecipes.permission

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.team23.neuracrsrecipes.R
import java.io.File
import java.util.Objects.requireNonNull

class ComposeFileProvider : FileProvider(
   R.xml.provider_paths
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val tempFile = File.createTempFile(
                "picture_${System.currentTimeMillis()}", ".png", context.cacheDir
            ).apply {
                createNewFile()
            }
            val authority = context.applicationContext.packageName + ".provider"
            return getUriForFile(
                requireNonNull(context),
                authority,
                tempFile,
            )
        }
    }
}
