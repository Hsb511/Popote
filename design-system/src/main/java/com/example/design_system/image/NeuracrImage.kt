package com.example.design_system.image

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.design_system.R
import com.example.design_system.theming.NeuracrTheme

@Composable
fun NeuracrImage(
    neuracrImageProperty: NeuracrImageProperty,
    modifier: Modifier = Modifier,
) {
    val imageModifier = modifier
        .clip(shape = MaterialTheme.shapes.medium)
    when (neuracrImageProperty) {
        is NeuracrImageProperty.Resource -> Image(
            painter = painterResource(neuracrImageProperty.imageRes),
            contentDescription = neuracrImageProperty.contentDescription,
            modifier = imageModifier,
        )
        is NeuracrImageProperty.Remote -> AsyncImage(
            model = neuracrImageProperty.url,
            contentDescription = neuracrImageProperty.contentDescription,
            modifier = imageModifier,
        )
        is NeuracrImageProperty.None -> {}
    }
}

@Composable
@Preview(showBackground = true)
private fun ResourceNeuracrImagePreview() {
    NeuracrTheme {
        NeuracrImage(
            NeuracrImageProperty.Resource(
                contentDescription = null,
                imageRes = R.drawable.bretzel
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun RemoteNeuracrImagePreview() {
    NeuracrTheme {
        NeuracrImage(
            NeuracrImageProperty.Remote(
                contentDescription = null,
                url = "https://neuracr.github.io/recipes/2022/03/06/bretzels.html"
            )
        )
    }
}
