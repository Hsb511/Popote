package com.example.design_system.flags

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

@Composable
fun NeuracrFlag(
	neuracrFlagProperty: NeuracrFlagProperty,
	modifier: Modifier = Modifier) {
	when (neuracrFlagProperty) {
		NeuracrFlagProperty.FRENCH -> FrenchFlag(modifier)
		NeuracrFlagProperty.UK_US -> UkUsFlag(modifier)
	}
}

@Composable
@Preview(showBackground = true)
private fun NeuracrFlagPreview(@PreviewParameter(SampleFlagProvider::class) neuracrFlagProperty: NeuracrFlagProperty) {
	NeuracrFlag(neuracrFlagProperty, Modifier
		.height(200.dp)
		.width(300.dp))
}

private class SampleFlagProvider : PreviewParameterProvider<NeuracrFlagProperty> {
	override val values = com.example.design_system.flags.NeuracrFlagProperty.values().asSequence()
}
