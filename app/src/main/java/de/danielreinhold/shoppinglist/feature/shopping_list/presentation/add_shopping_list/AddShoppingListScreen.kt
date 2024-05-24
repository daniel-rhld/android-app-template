package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_shopping_list

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
fun AddShoppingListScreen(
    uiState: AddShoppingListUiState,
    onUiEvent: (AddShoppingListUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.add_shopping_list_title),
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
                    AddShoppingListUiEvent.ShoppingListNameChange(value = value)
                )
            },
            label = {
                Text(text = stringResource(id = R.string.add_shopping_list_text_field_name_label))
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
                    onUiEvent.invoke(AddShoppingListUiEvent.ButtonSaveClick)
                },
                enabled = uiState.buttonSaveEnabled
            ) {
                Text(
                    text = stringResource(id = R.string.add_shopping_list_button_create_label)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewAddShoppingListScreen() {
    AppTheme {
        AddShoppingListScreen(
            uiState = AddShoppingListUiState(
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