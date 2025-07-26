package com.team23.view.ds.flag

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.FlagProperty

@Composable
fun PopoteFlag(
    flagProperty: FlagProperty,
    modifier: Modifier = Modifier
) {
    when (flagProperty) {
        FlagProperty.FRENCH -> FrenchFlag(modifier)
        FlagProperty.UK_US -> UkUsFlag(modifier)
    }
}
