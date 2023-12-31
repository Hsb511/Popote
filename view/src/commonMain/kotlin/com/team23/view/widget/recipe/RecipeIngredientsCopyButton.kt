package com.team23.view.widget.recipe

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.ds.icon.NeuracrIcon
import com.team23.view.extension.stringResource

@Composable
fun RecipeIngredientsCopyButton(
    ingredientsList: List<String>,
    modifier: Modifier = Modifier,
) {
    val clipboardManager = LocalClipboardManager.current

    IconButton(
        onClick = {
            clipboardManager.setText(
                AnnotatedString(
                    ingredientsList.joinToString(
                        prefix = " ${Typography.bullet} ",
                        separator = "\n ${Typography.bullet} "
                    )
                )
            )
        },
        modifier = modifier.offset(x = 8.dp, y = 8.dp)
    ) {
        NeuracrIcon(
            iconProperty = IconProperty.Resource(
                fileName = "drawable/ic_content_copy.xml",
                contentDescription = stringResource(id = "recipe_copy_to_clipboard_a11y"),
                tint = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f),
            )
        )
    }
}
