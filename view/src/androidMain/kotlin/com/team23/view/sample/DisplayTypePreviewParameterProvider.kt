package com.team23.view.sample

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.team23.neuracrsrecipes.model.property.DisplayType

class SampleDisplayTypeProvider : PreviewParameterProvider<DisplayType> {
    override val values = DisplayType.entries.asSequence()
}
