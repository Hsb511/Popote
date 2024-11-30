package com.team23.view.widget.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team23.view.ds.shimmer.Shimmer
import com.team23.view.extension.horizontalGutterPadding

@Composable
fun RecipeContentLoading(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = horizontalGutterPadding, vertical = 16.dp)) {
        Shimmer(
            textStyle = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
        )
        Divider(modifier = Modifier.padding(top = 8.dp))
        Shimmer(
            fontSize = 14.sp,
            modifier = Modifier
                .padding(vertical = 4.dp)
                .width(300.dp)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            List(3) {}.forEach { _ ->
                Shimmer(
                    modifier = Modifier
                        .height(32.dp)
                        .width(80.dp)
                )
            }
        }
        Shimmer(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .height(480.dp),
        )
        Shimmer(
            textStyle = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .width(160.dp)
                .padding(top = 16.dp, bottom = 8.dp)
        )
        Shimmer(
            modifier = Modifier.fillMaxWidth().height(1000.dp)
        )
    }
}
