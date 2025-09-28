package com.team23.view.preview.ds

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.view.ds.image.PopoteImage
import com.team23.view.sample.property.remoteImagePreviewSample
import com.team23.view.sample.property.resourceImagePreviewSample
import com.team23.view.theme.PopoteTheme


@Composable
@Preview(showBackground = true)
private fun ResourceImagePreview() {
    PopoteTheme {
        PopoteImage(resourceImagePreviewSample, 120.dp)
    }
}

@Composable
@Preview(showBackground = true)
private fun RemoteImagePreview() {
    PopoteTheme {
        PopoteImage(remoteImagePreviewSample, 120.dp)
    }
}
