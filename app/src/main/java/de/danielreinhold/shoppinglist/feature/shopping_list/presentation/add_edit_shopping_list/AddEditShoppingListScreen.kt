package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_edit_shopping_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.danielreinhold.shoppinglist.R
import de.danielreinhold.shoppinglist.core.presentation.theme.AppTheme

@Composable
fun AddEditShoppingListScreen(
    uiState: AddEditShoppingListUiState,
    onUiEvent: (AddEditShoppingListUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(
                id = if (uiState.shoppingListId == null)
                    R.string.add_shopping_list_title
                else
                    R.string.edit_shopping_list_title
            ),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.shoppingListName,
            onValueChange = { value ->
                onUiEvent.invoke(
                    AddEditShoppingListUiEvent.ShoppingListNameChange(value = value)
                )
            },
            label = {
                Text(text = stringResource(id = R.string.add_edit_shopping_list_text_field_name_label))
            },
            shape = RoundedCornerShape(
                size = 8.dp
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    onUiEvent.invoke(AddEditShoppingListUiEvent.ButtonSaveClick)
                },
                enabled = uiState.buttonSaveEnabled
            ) {
                Text(
                    text = stringResource(
                        id = if (uiState.shoppingListId == null)
                            R.string.add_edit_shopping_list_button_create_label
                        else
                            R.string.add_edit_shopping_list_button_update_label
                    )
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewAddShoppingListScreen() {
    AppTheme {
        AddEditShoppingListScreen(
            uiState = AddEditShoppingListUiState(
                shoppingListId = null,
                shoppingListName = "Test",
                buttonSaveEnabled = true
            ),
            onUiEvent = {},
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(all = 16.dp)
        )
    }
}