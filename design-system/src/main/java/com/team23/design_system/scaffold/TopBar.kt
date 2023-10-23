package com.team23.design_system.scaffold

import android.content.Context
import android.view.View
import android.view.Window
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ComponentActivity
import com.team23.design_system.R
import com.team23.design_system.theming.NeuracrTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
	isNavigationEmpty: Boolean,
	scrollState: ScrollState,
	heightToBeFaded: Float,
	title: String?,
	navigateUp: () -> Unit,
	drawerState: DrawerState,
	openMenu: () -> Unit,
	closeMenu: () -> Unit,
) {
	val alpha = computeDefaultTitleAlpha(scrollState.value, heightToBeFaded, title)
	if (alpha == 0f) {
		val localView = LocalView.current
		SideEffect {
			hideStatusBar(localView)
		}
	}
	CenterAlignedTopAppBar(
		colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
			containerColor = MaterialTheme.colorScheme.primary.copy(alpha),
			navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
			titleContentColor = MaterialTheme.colorScheme.onPrimary,
			actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
		),
		navigationIcon = {
			NavigationIcon(isNavigationEmpty, navigateUp, closeMenu)
		},
		title = {
			Title(scrollState, heightToBeFaded, title)
		},
		actions = {
			Actions(drawerState, openMenu, closeMenu)
		},
		modifier = Modifier.background(
			brush = Brush.verticalGradient(
				listOf(
					MaterialTheme.colorScheme.primary,
					MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
					MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
					MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
					Color.Transparent,
				)
			)
		)
	)
}

@Composable
private fun NavigationIcon(
	isNavigationEmpty: Boolean,
	navigateUp: () -> Unit,
	closeMenu: () -> Unit,
) {
	if (isNavigationEmpty) {
		Image(
			contentScale = ContentScale.FillHeight,
			painter = painterResource(id = R.drawable.neuracr_logo),
			contentDescription = null,
			modifier = Modifier
				.height(60.dp)
				.padding(vertical = 6.dp)
		)
	} else {
		IconButton(onClick = {
			navigateUp()
			closeMenu()
		}) {
			Icon(
				imageVector = Icons.Filled.ArrowBack,
				contentDescription = stringResource(id = R.string.scaffold_navigate_up_a11y)
			)
		}
	}
}

@Composable
private fun Title(scrollState: ScrollState, heightToBeFaded: Float, title: String?) {
	Box(contentAlignment = Alignment.Center) {

		Text(
			text = stringResource(R.string.scaffold_title),
			style = MaterialTheme.typography.headlineSmall,
			modifier = Modifier.graphicsLayer {
				alpha = computeDefaultTitleAlpha(scrollState.value, heightToBeFaded, title)
			}
		)
		if (title != null && scrollState.value != 0 && heightToBeFaded != 0f) {
			Text(
				text = title,
				style = MaterialTheme.typography.headlineSmall.copy(
					lineHeight = 26.sp
				),
				textAlign = TextAlign.Center,
				maxLines = 2,
				modifier = Modifier
					.padding(horizontal = 8.dp)
					.graphicsLayer {
						alpha = computeTitleAlpha(scrollState.value, heightToBeFaded)
					}
			)
		}
	}
}

private fun computeDefaultTitleAlpha(
	scroll: Int,
	heightToBeFaded: Float,
	title: String?,
): Float = when {
	title == null -> 1f
	heightToBeFaded > 0 -> (1 - 2 / heightToBeFaded * scroll).coerceIn(0f, 1f)
	else -> 0f
}

private fun computeTitleAlpha(scroll: Int, heightToBeFaded: Float): Float = if (heightToBeFaded > 0) {
	(2 / (2 * heightToBeFaded) * scroll).coerceIn(0f, 1f)
} else {
	1f
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Actions(
	drawerState: DrawerState,
	openMenu: () -> Unit,
	closeMenu: () -> Unit,
) {
	Crossfade(targetState = drawerState.currentValue == DrawerValue.Open, label = "settings") { isDrawerOpen ->
		if (isDrawerOpen) {
			IconButton(onClick = closeMenu) {
				Icon(
					imageVector = Icons.Filled.Close,
					contentDescription = stringResource(id = R.string.scaffold_close_menu_a11y),
					tint = MaterialTheme.colorScheme.onPrimary,
				)
			}
		} else {
			IconButton(onClick = openMenu) {
				Icon(
					imageVector = Icons.Filled.Menu,
					contentDescription = stringResource(id = R.string.scaffold_open_menu_a11y),
					tint = MaterialTheme.colorScheme.onPrimary,
				)
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun TopBarPreview() {
	NeuracrTheme {
		TopBar(
			isNavigationEmpty = true,
			heightToBeFaded = 0f,
			title = "Bretzels",
			scrollState = rememberScrollState(),
			navigateUp = {},
			drawerState = DrawerState(DrawerValue.Closed),
			openMenu = {},
			closeMenu = {},
		)
	}
}

private fun hideStatusBar(localView: View) {
	localView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	getWindow(localView.context)?.let { window ->
		window.statusBarColor = Color.Transparent.toArgb()
	}
}

private fun getWindow(context: Context): Window? = when (context) {
	is ComponentActivity -> context
	else -> null
}?.window
