package com.team23.view.ds.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.action.CellAction
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.ColorProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.Res
import com.team23.view.ds.button.ButtonLike
import com.team23.view.ds.button.ButtonLocalPhone
import com.team23.view.ds.image.PopoteImage
import com.team23.view.extension.getImageMaxHeight
import com.team23.view.ic_language
import com.team23.view.ic_local_smartphone
import com.team23.view.locally_saved_button_content_description
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun CellCard(
    cellProperty: CellProperty,
    displayType: DisplayType,
    modifier: Modifier = Modifier,
    onAction: (CellAction) -> Unit = {},
) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
        Box(modifier = modifier.clip(shape = MaterialTheme.shapes.medium)) {
            PopoteImage(
                neuracrImageProperty = cellProperty.imageProperty,
                maxImageHeight = getImageMaxHeight(),
                displayType = displayType,
                contentScale = cellProperty.maxHeight?.let { ContentScale.Fit } ?: ContentScale.FillWidth,
                modifier = cellProperty.maxHeight
                    ?.let { maxHeight -> Modifier.widthIn(max = maxHeight * 3f / 2f).height(maxHeight) }
                    ?: Modifier.fillMaxWidth()
            )
            if (cellProperty.isLocallySaved) {
                ButtonLocalPhone(
                    localPhone = CellProperty.LocalPhone(
                        iconProperty = IconProperty.Resource(
                            drawableResource = Res.drawable.ic_local_smartphone,
                            contentDescription = Res.string.locally_saved_button_content_description,
                            tint = ColorProperty.DefaultIcon,
                        ),
                    ),
                    modifier = Modifier.align(Alignment.TopStart).offset(x = (-8).dp, y = (-8).dp),
                    onClick = { onAction(CellAction.LocalPhoneClick) }
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(end = 32.dp)
            ) {
                CellFlag(
                    flagProperty = cellProperty.languageFlag,
                    withLanguageWatermark = true,
                    bottomStartCorner = 0.dp,
                )
                Text(
                    text = cellProperty.title,
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .clip(
                            shape = MaterialTheme.shapes.medium.copy(
                                topStart = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp),
                            )
                        )
                        .background(color = MaterialTheme.colorScheme.tertiary)
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 2.dp)
                )
            }
            androidx.compose.animation.AnimatedVisibility(
                visible =  cellProperty.cuisineFlag != null,
                modifier = Modifier.align(Alignment.TopEnd),
            ) {
                cellProperty.cuisineFlag?.let { cuisineFlag ->
                    CellFlag(
                        flagProperty = cuisineFlag,
                        topStartCorner = 0.dp,
                    )
                }
            }

            ButtonLike(
                iconProperty = cellProperty.favorite.iconProperty,
                onFavoriteClick = { onAction(cellProperty.favorite.action) },
                modifier = Modifier.align(Alignment.BottomEnd).offset(x = 8.dp, y = 8.dp)
            )
        }
    }
}
