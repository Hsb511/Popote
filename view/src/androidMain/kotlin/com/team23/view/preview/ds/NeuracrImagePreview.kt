package com.team23.view.preview.ds

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.view.ds.image.NeuracrImage
import com.team23.view.sample.remoteImagePreviewSample
import com.team23.view.sample.resourceImagePreviewSample
import com.team23.view.theme.PopoteTheme


@Composable
@Preview(showBackground = true)
private fun ResourceNeuracrImagePreview() {
    PopoteTheme {
        NeuracrImage(resourceImagePreviewSample, 120.dp)
    }
}

@Composable
@Preview(showBackground = true)
private fun RemoteNeuracrImagePreview() {
    PopoteTheme {
        NeuracrImage(remoteImagePreviewSample, 120.dp)
    }
}
