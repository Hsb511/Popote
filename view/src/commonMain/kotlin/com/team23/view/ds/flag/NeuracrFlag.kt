package com.team23.view.ds.flag

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.FlagProperty

@Composable
fun NeuracrFlag(
    neuracrFlagProperty: FlagProperty,
    modifier: Modifier = Modifier
) {
    when (neuracrFlagProperty) {
        FlagProperty.FRENCH -> FrenchFlag(modifier)
        FlagProperty.UK_US -> UkUsFlag(modifier)
    }
}
