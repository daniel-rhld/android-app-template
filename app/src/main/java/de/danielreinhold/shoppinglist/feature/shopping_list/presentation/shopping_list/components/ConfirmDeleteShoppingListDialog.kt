package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import de.danielreinhold.shoppinglist.R
import de.danielreinhold.shoppinglist.core.presentation.theme.AppTheme


/*



 */

@Composable
fun ConfirmDeleteShoppingListDialog(
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(id = R.string.confirmation_dialog_ok)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(
                    text = stringResource(id = R.string.confirmation_dialog_cancel)
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.delete_shopping_list_confirmation_dialog_title)
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.delete_shopping_list_confirmation_dialog_text)
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = null
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun PreviewConfirmDeleteShoppingListDialog() {
    AppTheme {
        ConfirmDeleteShoppingListDialog(
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}