package com.team23.view.preview.ds

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.view.ds.flag.PopoteFlag

@Composable
@Preview(showBackground = true)
private fun PopoteFlagPreview(@PreviewParameter(FlagPreviewParameterProvider::class) flagProperty: FlagProperty) {
    PopoteFlag(
        flagProperty = flagProperty,
        modifier = Modifier.size(width = 300.dp, height = 200.dp)
    )
}

internal class FlagPreviewParameterProvider : PreviewParameterProvider<FlagProperty> {
    override val values = FlagProperty.entries.asSequence()
}
