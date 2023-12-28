package com.team23.view.preview.ds

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.team23.view.ds.shimmer.NeuracrShimmer
import com.team23.view.theme.PopoteTheme

@Composable
@Preview(showSystemUi = true)
private fun NeuracrShimmerPreview() {
    PopoteTheme {
        NeuracrShimmer()
    }
}
