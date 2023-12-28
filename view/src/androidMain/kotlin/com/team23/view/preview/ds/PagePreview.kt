package com.team23.view.preview.ds

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.team23.neuracrsrecipes.model.uimodel.ErrorPageUiModel
import com.team23.view.ErrorPage
import com.team23.view.InProgressPage
import com.team23.view.theme.PopoteTheme

@Composable
@Preview(showSystemUi = true)
fun PageInProgressPreview() {
    PopoteTheme {
        InProgressPage()
    }
}

@Composable
@Preview(showSystemUi = true)
fun NeuracrErrorPreview() {
    PopoteTheme {
        ErrorPage(errorPageUiModel = ErrorPageUiModel("An error occured") {})
    }
}
