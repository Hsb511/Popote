package com.team23.view.ds.scaffold

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.team23.view.ds.icon.FillSearchIcon

@Composable
internal fun BottomBar(navItemProperties: List<NavItemProperty>, closeMenu: () -> Unit) {
	NavigationBar(
		modifier = Modifier.height(56.dp)
	) {
		navItemProperties.map { navItemProperty ->
			NavigationBarItem(
				selected = navItemProperty.isSelected,
				onClick = {
					navItemProperty.onNavigate()
					closeMenu()
				},
				icon = {
					Crossfade(targetState = navItemProperty.icon, animationSpec = tween(500)) { imageVector ->
						if (imageVector == Icons.Filled.Search) {
							FillSearchIcon(tint = MaterialTheme.colorScheme.onSurface)
						} else {
							Icon(
								imageVector = imageVector,
								contentDescription = null,
							)
						}
					}
				},
				label = {
					Text(
						text = navItemProperty.title,
						fontWeight = if (navItemProperty.isSelected) FontWeight.ExtraBold else null,
						modifier = Modifier.offset(y = 22.dp)
					)
				},
				colors = NavigationBarItemDefaults.colors(
					selectedIconColor = MaterialTheme.colorScheme.onSurface,
					selectedTextColor = MaterialTheme.colorScheme.onSurface,
					indicatorColor = MaterialTheme.colorScheme.secondary,
					unselectedIconColor = MaterialTheme.colorScheme.scrim,
					unselectedTextColor = MaterialTheme.colorScheme.scrim,
				),
				modifier = Modifier.offset(y = (-8).dp)
			)
		}
	}
}

data class NavItemProperty(
	val title: String,
	val icon: ImageVector,
	val isSelected: Boolean,
	val onNavigate: () -> Unit
)
