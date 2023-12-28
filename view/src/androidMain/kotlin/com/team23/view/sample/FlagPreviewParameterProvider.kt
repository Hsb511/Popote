package com.team23.view.sample

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.team23.neuracrsrecipes.model.property.FlagProperty

internal class FlagPreviewParameterProvider : PreviewParameterProvider<FlagProperty> {
    override val values = FlagProperty.entries.asSequence()
}
