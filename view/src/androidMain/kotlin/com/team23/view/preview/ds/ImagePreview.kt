package com.team23.view.preview.ds

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.view.ds.image.NeuracrImage
import com.team23.view.sample.property.remoteImagePreviewSample
import com.team23.view.sample.property.resourceImagePreviewSample
import com.team23.view.theme.PopoteTheme


@Composable
@Preview(showBackground = true)
private fun ResourceImagePreview() {
    PopoteTheme {
        NeuracrImage(resourceImagePreviewSample, 120.dp)
    }
}

@Composable
@Preview(showBackground = true)
private fun RemoteImagePreview() {
    PopoteTheme {
        NeuracrImage(remoteImagePreviewSample, 120.dp)
    }
}
