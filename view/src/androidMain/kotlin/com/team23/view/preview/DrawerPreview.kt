package com.team23.view.preview

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.team23.neuracrsrecipes.model.uimodel.DrawerUiModel
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.drawer.DrawerFooter
import com.team23.view.widget.drawer.ModalMenuDrawer
import com.team23.view.widget.drawer.NicknameDrawerItem


@Composable
@Preview(showBackground = true)
fun DrawerFooterPreview() {
    PopoteTheme {
        DrawerFooter("2.3.23")
    }
}

@Composable
@Preview(showSystemUi = true)
fun ModalMenuDrawerPreview() {
    PopoteTheme {
        ModalMenuDrawer(
            drawerUiModel = DrawerUiModel(
                // rememberDrawerState(initialValue = DrawerValue.Open),
                versionName = "23.23.0",
            ),
            drawerState = rememberDrawerState(DrawerValue.Open),
        ) {}
    }
}

@Composable
@Preview(showBackground = true)
fun NicknameDrawerItemPreview() {
    MaterialTheme {
        NicknameDrawerItem("GOGO", {})
    }
}
