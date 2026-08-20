package com.team23.view.ds.cell

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import com.team23.neuracrsrecipes.model.property.FlagProperty
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.action.CellAction
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.view.ds.button.ButtonGroceryList
import com.team23.view.ds.button.ButtonLike
import com.team23.view.ds.image.PopoteImage
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun CellList(
	cellProperty: CellProperty,
	modifier: Modifier = Modifier,
	onAction: (CellAction) -> Unit = {},
) {
	Card(
		elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
		modifier = modifier.height(64.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxSize(),
		) {
			Box {
				PopoteImage(
					neuracrImageProperty = cellProperty.imageProperty,
					maxImageHeight = 200.dp,
					hasNoCornerEnd = true,
					contentScale = ContentScale.FillBounds,
					modifier = Modifier
						.fillMaxHeight()
						.width(64.dp),
				)
				CellFlag(
					flagProperty = cellProperty.languageFlag,
					withLanguageWatermark = true,
					modifier = Modifier.align(Alignment.BottomStart)
				)
			}
			Box(modifier = Modifier.fillMaxSize()) {
				Text(
					text = cellProperty.title,
					color = MaterialTheme.colorScheme.onBackground,
					style = MaterialTheme.typography.titleSmall,
					modifier = Modifier
						.padding(all = 8.dp)
						.align(Alignment.CenterStart)
				)

				AnimatedCellFlag(
                    cuisineFlag = cellProperty.cuisineFlag,
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                )

				Row(
					modifier = Modifier
						.align(Alignment.BottomEnd)
						.offset(x = 8.dp, y = 8.dp)
				) {
					ButtonGroceryList(
						iconProperty = cellProperty.groceryList.iconProperty,
						onGroceryListClick = { onAction(cellProperty.groceryList.action) },
						modifier = Modifier.offset(x = 16.dp)
					)
					ButtonLike(
						iconProperty = cellProperty.favorite.iconProperty,
						onFavoriteClick = { onAction(cellProperty.favorite.action) },
					)
				}
			}
		}
	}
}

@Composable
private fun AnimatedCellFlag(
    cuisineFlag: FlagProperty?,
    modifier: Modifier = Modifier,
) {
    var cuisineFlagVisible by remember { mutableStateOf(false) }

    LaunchedEffect(cuisineFlag) {
        delay(ANIMATION_DURATION_MILLIS.milliseconds)
		cuisineFlagVisible = cuisineFlag != null
    }

	AnimatedVisibility(
		visible = cuisineFlagVisible,
		enter = expandIn(expandFrom = Alignment.TopEnd, animationSpec = tween(ANIMATION_DURATION_MILLIS)),
		exit = shrinkOut(shrinkTowards = Alignment.TopEnd, animationSpec = tween(ANIMATION_DURATION_MILLIS)),
        modifier = modifier,
	) {
		cuisineFlag?.let { flag ->
            CellFlag(
                flagProperty = flag,
            )
        }
	}
}

private const val ANIMATION_DURATION_MILLIS = 300
