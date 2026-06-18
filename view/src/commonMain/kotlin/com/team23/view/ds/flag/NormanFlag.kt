package com.team23.view.ds.flag

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.view.Res
import com.team23.view.flag_norman
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun NormanFlag(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            imageVector = vectorResource(Res.drawable.flag_norman),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
