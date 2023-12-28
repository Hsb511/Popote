package com.team23.view.preview.ds

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.view.ds.display.DisplayBigCard
import com.team23.view.ds.display.DisplayList
import com.team23.view.ds.display.DisplaySmallCard
import com.team23.view.theme.PopoteTheme

@Composable
@Preview(showBackground = true)
fun DisplayBigCardPreview() {
    PopoteTheme {
        DisplayBigCard(
            tint = Color.Gray,
            modifier = Modifier.size(100.dp)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun DisplayListPreview() {
    PopoteTheme {
        DisplayList(
            tint = Color.Gray,
            modifier = Modifier.size(100.dp)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun DisplaySmallCardPreview() {
    PopoteTheme {
        DisplaySmallCard(
            tint = Color.Gray,
            modifier = Modifier.size(100.dp)
        )
    }
}
