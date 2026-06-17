package com.team23.view.preview

import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Preview(
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
)
annotation class PreviewWithSystemUi

@Preview(showBackground = true)
@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF000000,
)
annotation class PreviewWithBackground