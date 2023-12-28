package com.team23.view.preview.ds

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.ds.button.ButtonTextDialog
import com.team23.view.ds.button.ButtonLike
import com.team23.view.ds.button.ButtonLocalPhone
import com.team23.view.sample.ButtonLikePreviewParameterProvider
import com.team23.view.theme.PopoteTheme


@Composable
@Preview(showBackground = true)
private fun ButtonLikePreview(@PreviewParameter(ButtonLikePreviewParameterProvider::class) iconProperty: IconProperty.Vector) {
    PopoteTheme{
        ButtonLike(iconProperty, {})
    }
}


@Composable
@Preview(showBackground = true)
private fun ButtonLocalPhonePreview() {
    PopoteTheme {
        ButtonLocalPhone(
            CellProperty.LocalPhone(
                iconProperty = 	IconProperty.Resource(
                    fileName = "drawable/ic_local_smartphone.xml",
                    contentDescription = "contentDescription",
                    tint = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f),
                ),
                onLocalPhoneClick = {},
            )
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun ButtonTextDialogPreview() {
    PopoteTheme {
        ButtonTextDialog("DISMISS") {}
    }
}