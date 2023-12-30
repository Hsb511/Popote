package com.team23.view.preview.ds

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.team23.view.ds.shimmer.Shimmer
import com.team23.view.theme.PopoteTheme

@Composable
@Preview(showSystemUi = true)
private fun ShimmerPreview() {
    PopoteTheme {
        Shimmer()
    }
}
