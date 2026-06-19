package com.team23.view.widget.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.team23.view.Res
import com.team23.view.add_recipe_select_image_source_camera
import com.team23.view.add_recipe_select_image_source_gallery
import com.team23.view.add_recipe_select_image_source_title
import com.team23.view.ic_camera
import com.team23.view.ic_gallery
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ImageSourceOptionDialog(
    onDismissRequest: () -> Unit,
    onGalleryRequest: () -> Unit = {},
    onCameraRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.large,
                )
                .padding(20.dp),
        ) {
            Text(
                text = stringResource(Res.string.add_recipe_select_image_source_title),
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
                    .clickable(enabled = false) {
                        onCameraRequest.invoke()
                    },
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(Res.drawable.ic_camera),
                    contentDescription = null
                )
                Text(
                    text = stringResource(Res.string.add_recipe_select_image_source_camera),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
                    .clickable {
                        onGalleryRequest.invoke()
                    },
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(Res.drawable.ic_gallery),
                    contentDescription = null
                )
                Text(
                    text = stringResource(Res.string.add_recipe_select_image_source_gallery),
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}
