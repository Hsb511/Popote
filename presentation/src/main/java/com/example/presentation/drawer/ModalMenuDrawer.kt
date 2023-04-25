package com.example.presentation.drawer

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theming.NeuracrTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalMenuDrawer(drawerState: DrawerState, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
	val context = LocalContext.current
	ModalNavigationDrawer(
		modifier = modifier,
		drawerState = drawerState,
		gesturesEnabled = false,
		drawerContent = {
			ModalDrawerSheet {
				Column(modifier = Modifier.fillMaxSize()) {
					Spacer(modifier = Modifier.weight(1f))
					Row(modifier = Modifier.padding(all = 4.dp)) {
						Text(
							text = "© ",
						)
						ClickableText(
							text = AnnotatedString("neuracr"),
							style = MaterialTheme.typography.bodyLarge.copy(
								color = MaterialTheme.colorScheme.onSecondary,
								textDecoration = TextDecoration.Underline,
							),
							onClick = {
								launchNewGithubIntent(context,"neuracr")
							},
						)
						Text(
							text = " & "
						)
						ClickableText(
							text = AnnotatedString("Hsb511"),
							style = MaterialTheme.typography.bodyLarge.copy(
								color = MaterialTheme.colorScheme.onSecondary,
								textDecoration = TextDecoration.Underline,
							),
							onClick = {
								launchNewGithubIntent(context,"Hsb511")
							}
						)
					}
				}
			}
		},
		content = content,
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun ModalMenuDrawerPreview() {
	NeuracrTheme {
		ModalMenuDrawer(rememberDrawerState(initialValue = DrawerValue.Open)) {}
	}
}

private fun launchNewGithubIntent(context: Context, nickname: String) {
	Intent().apply {
		action = Intent.ACTION_VIEW
		data = Uri.parse("https://github.com/$nickname")
	}.also { intent ->
		context.startActivity(intent)
	}
}
