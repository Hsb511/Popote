package com.team23.design_system.display

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

enum class DisplayType {
	BigCard,
	SmallCard,
	List,
}

class SampleDisplayTypeProvider : PreviewParameterProvider<DisplayType> {
	override val values = DisplayType.values().asSequence()
}