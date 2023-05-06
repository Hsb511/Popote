package com.team23.presentation.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.R
import com.team23.design_system.flags.NeuracrFlag
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImage
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.presentation.home.models.SummarizedRecipeUiModel

@Composable
internal fun HomeRecipeCard(
    summarizedRecipeUiModel: SummarizedRecipeUiModel,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Box(
            modifier = modifier.clip(shape = MaterialTheme.shapes.medium)
        ) {
            NeuracrImage(
                neuracrImageProperty = summarizedRecipeUiModel.imageProperty,
                maxImageHeight =  (LocalConfiguration.current.screenWidthDp.dp - 64.dp) * 3 / 4,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = summarizedRecipeUiModel.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clip(shape = MaterialTheme.shapes.medium.copy(
                        topStart = CornerSize(0.dp),
                        bottomEnd = CornerSize(0.dp)
                    ))
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(start = 12.dp, bottom = 2.dp, end = 12.dp)
            )
            NeuracrFlag(
                neuracrFlagProperty = summarizedRecipeUiModel.flagProperty,
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
}

@Composable
@Preview(showBackground = true)
private fun SummarizedRecipeCardPreview() {
    HomeRecipeCard(
        SummarizedRecipeUiModel(
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
