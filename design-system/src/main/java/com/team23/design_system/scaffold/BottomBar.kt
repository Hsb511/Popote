package com.team23.design_system.scaffold

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme

@Composable
internal fun BottomBar(navItemProperties: List<NavItemProperty>, closeMenu: () -> Unit) {
	NavigationBar(
		containerColor = MaterialTheme.colorScheme.inverseSurface,
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
					Icon(
						imageVector = navItemProperty.icon,
						contentDescription = null,
					)
				},
				label = {
					Text(
						text = navItemProperty.title,
						fontWeight = if (navItemProperty.isSelected) FontWeight.ExtraBold else null,
						modifier = Modifier.offset(y = 22.dp)
					)
				},
				colors = NavigationBarItemDefaults.colors(
					selectedIconColor = MaterialTheme.colorScheme.inverseOnSurface,
					selectedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
					indicatorColor = MaterialTheme.colorScheme.secondary,
					unselectedIconColor = MaterialTheme.colorScheme.scrim,
					unselectedTextColor = MaterialTheme.colorScheme.scrim,
				),
				modifier = Modifier.offset(y = (-8).dp)
			)
		}
	}
}

@Composable
@Preview(showBackground = true)
private fun BottomBarPreview() {
	NeuracrTheme {
		BottomBar(
			listOf(
				NavItemProperty("Home", Icons.Filled.Home, true) {},
				NavItemProperty("Search", Icons.Outlined.Search, false) {},
				NavItemProperty("About", Icons.Outlined.Info, false) {},
			)
		) {}
	}
}

data class NavItemProperty(
	val title: String,
	val icon: ImageVector,
	val isSelected: Boolean,
	val onNavigate: () -> Unit
)
