package com.team23.view.widget.grocery

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.view.Res
import com.team23.view.ds.image.PopoteImage
import com.team23.view.extension.horizontalGutterPadding
import com.team23.view.ic_grocery_list_add
import com.team23.view.neuracr_wip
import com.team23.view.grocery_list_empty_description_part1
import com.team23.view.grocery_list_empty_description_part2
import com.team23.view.grocery_list_empty_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GroceryListEmpty() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = horizontalGutterPadding),
    ) {
        Text(
            text = stringResource(Res.string.grocery_list_empty_title),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )
        PopoteImage(
            neuracrImageProperty = ImageProperty.Resource(
                contentDescription = null,
                drawableResource = Res.drawable.neuracr_wip,
            ),
            maxImageHeight = 230.dp,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        val description = buildAnnotatedString {
            append(stringResource(Res.string.grocery_list_empty_description_part1) + " ")
            appendInlineContent(id = "list")
            append(" " + stringResource(Res.string.grocery_list_empty_description_part2))
        }
        val inlineContentMap = mapOf(
            "list" to InlineTextContent(
                Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_grocery_list_add),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme())
                        MaterialTheme.colorScheme.onBackground
                    else
                        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f),
                    modifier = Modifier.fillMaxSize(),
                )
            }
        )
        Text(
            text = description,
            inlineContent = inlineContentMap,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
    }
}