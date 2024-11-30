package com.team23.view.widget.favorite

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.view.Res
import com.team23.view.ds.display.DisplayBigCard
import com.team23.view.ds.display.DisplayList
import com.team23.view.ds.display.DisplaySmallCard
import com.team23.view.extension.next
import com.team23.view.favorite_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun FavoriteHeader(
    displayType: DisplayType,
    onDisplayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val headerPadding = if (displayType == DisplayType.BigCard) 0.dp else 16.dp

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(top = headerPadding, start = headerPadding, end = headerPadding)
    ) {
        Text(
            text = stringResource(Res.string.favorite_title),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onDisplayClick,
            modifier = Modifier.offset(x = 8.dp, y = (-4).dp)
        ) {
            val tint = MaterialTheme.colorScheme.onBackground
            val iconModifier = Modifier.padding(all = 8.dp)
            Crossfade(targetState = displayType.next(), animationSpec = tween(500)) { displayType ->
                when (displayType) {
                    DisplayType.BigCard -> DisplayBigCard(tint = tint, modifier = iconModifier)
                    DisplayType.SmallCard -> DisplaySmallCard(tint = tint, modifier = iconModifier)
                    DisplayType.List -> DisplayList(tint = tint, modifier = iconModifier)
                }
            }
        }
    }
}
