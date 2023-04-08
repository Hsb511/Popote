package com.example.presentation.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.R
import com.example.design_system.flags.NeuracrFlag
import com.example.design_system.flags.NeuracrFlagProperty
import com.example.design_system.image.NeuracrImage
import com.example.design_system.image.NeuracrImageProperty
import com.example.presentation.home.models.HomeRecipeUiModel

@Composable
internal fun HomeRecipeCard(
    homeRecipeUiModel: HomeRecipeUiModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clip(shape = MaterialTheme.shapes.medium)
    ) {
        NeuracrImage(
            neuracrImageProperty = homeRecipeUiModel.imageProperty,
            maxImageHeight =  (LocalConfiguration.current.screenWidthDp.dp - 64.dp) * 3 / 4,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = homeRecipeUiModel.title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .clip(shape = MaterialTheme.shapes.medium.copy(
                    topStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                ))
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = 12.dp)
        )
        NeuracrFlag(
            neuracrFlagProperty = homeRecipeUiModel.flagProperty,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(shape = MaterialTheme.shapes.medium.copy(
                    topStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                ))
                .width(30.dp)
                .height(20.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeRecipeCardPreview() {
    HomeRecipeCard(
        HomeRecipeUiModel(
            id = "",
            imageProperty = NeuracrImageProperty.Resource(
                contentDescription = null,
                imageRes = R.drawable.bretzel
            ),
            title = "Bretzels",
            flagProperty = NeuracrFlagProperty.FRENCH,
        ),
    )
}
