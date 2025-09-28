package com.team23.view.widget.drawer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp

@Composable
fun DrawerFooter(
    versionName: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.padding(all = 4.dp)) {
        val linkStyle = TextLinkStyles(
            SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textDecoration = TextDecoration.Underline
            )
        )
        Text(
            text = buildAnnotatedString {
                append("© ")
                withLink(LinkAnnotation.Url(url = "https://github.com/neuracr", styles = linkStyle)) {
                    append("neuracr")
                }
                append(" & ")
                withLink(LinkAnnotation.Url(url = "https://github.com/Hsb511", styles = linkStyle)) {
                    append("Hsb511")
                }
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "v$versionName",
        )
    }
}
