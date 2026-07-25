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
        FlagProperty.ALSATIAN -> AlsatianFlag(modifier)
        FlagProperty.BELGIAN -> BelgianFlag(modifier)
        FlagProperty.CHINESE -> ChineseFlag(modifier)
        FlagProperty.CUBAN -> CubanFlag(modifier)
        FlagProperty.FRENCH -> FrenchFlag(modifier)
        FlagProperty.GREEK -> GreekFlag(modifier)
        FlagProperty.HUNGARIAN -> HungarianFlag(modifier)
        FlagProperty.INDIAN -> IndianFlag(modifier)
        FlagProperty.ITALIAN -> ItalianFlag(modifier)
        FlagProperty.LEBANESE -> LebaneseFlag(modifier)
        FlagProperty.PUERTO_RICAN -> PuertoRicanFlag(modifier)
        FlagProperty.NORMAN -> NormanFlag(modifier)
        FlagProperty.THAI -> ThaiFlag(modifier)
        FlagProperty.TURKISH -> TurkishFlag(modifier)
        FlagProperty.UK_US -> UkUsFlag(modifier)
        FlagProperty.US -> UsFlag(modifier)
        FlagProperty.US_MEXICAN -> UsMexicanFlag(modifier)
    }
}
