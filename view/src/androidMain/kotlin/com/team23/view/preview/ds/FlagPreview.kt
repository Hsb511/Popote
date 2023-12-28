package com.team23.view.preview.ds

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.view.ds.flag.FrenchFlag
import com.team23.view.ds.flag.NeuracrFlag
import com.team23.view.ds.flag.UkUsFlag
import com.team23.view.sample.FlagPreviewParameterProvider

@Composable
@Preview(showBackground = true)
private fun NeuracrFlagPreview(@PreviewParameter(FlagPreviewParameterProvider::class) neuracrFlagProperty: FlagProperty) {
    NeuracrFlag(neuracrFlagProperty, flagPreviewModifier)
}

@Composable
@Preview(showBackground = true)
private fun FrenchFlagPreview() {
    FrenchFlag(modifier = flagPreviewModifier)
}

@Composable
@Preview(showBackground = true)
private fun UkUsaFlagPreview() {
    UkUsFlag(modifier = flagPreviewModifier)
}

private val flagPreviewModifier = Modifier.size(width = 300.dp, height = 200.dp)