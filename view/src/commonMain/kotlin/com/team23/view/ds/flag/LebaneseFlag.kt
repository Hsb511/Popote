package com.team23.view.ds.flag

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.view.Res
import com.team23.view.flag_lebanese
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun LebaneseFlag(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            imageVector = vectorResource(Res.drawable.flag_lebanese),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
