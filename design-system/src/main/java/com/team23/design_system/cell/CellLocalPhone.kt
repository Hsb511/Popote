package com.team23.design_system.cell

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.R
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun CellLocalPhone(modifier: Modifier = Modifier) {
	IconButton(
		onClick = { /* TODO */ },
		modifier = modifier.offset(x = (-8).dp, y = (-8).dp),
	) {
		Icon(
			painter = painterResource(id = R.drawable.ic_local_smartphone),
			contentDescription = stringResource(id = R.string.locally_saved_button_content_description),
			tint = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f),
		)
	}
}

@Composable
@Preview(showBackground = true)
fun CellLocalPhonePreview() {
	NeuracrTheme {
		CellLocalPhone()
	}
}
