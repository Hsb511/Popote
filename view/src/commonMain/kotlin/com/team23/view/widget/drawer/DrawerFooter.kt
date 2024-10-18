package com.team23.view.widget.drawer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun DrawerFooter(
    versionName: String,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current

    Row(modifier = modifier.padding(all = 4.dp)) {
        Text(
            text = "© ",
        )
        ClickableText(
            text = AnnotatedString("neuracr"),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textDecoration = TextDecoration.Underline,
            ),
            onClick = {
                uriHandler.openUri("https://github.com/neuracr")
            },
        )
        Text(
            text = " & "
        )
        ClickableText(
            text = AnnotatedString("Hsb511"),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textDecoration = TextDecoration.Underline,
            ),
            onClick = {
                uriHandler.openUri("https://github.com/Hsb511")
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "v$versionName",
        )
    }
}
