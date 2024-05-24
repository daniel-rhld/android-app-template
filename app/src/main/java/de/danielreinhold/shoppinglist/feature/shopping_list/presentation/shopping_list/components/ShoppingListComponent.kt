package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.danielreinhold.shoppinglist.R
import de.danielreinhold.shoppinglist.core.presentation.theme.AppTheme
import de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list.ShoppingListMockItem

@Composable
fun ShoppingListComponent(
    shoppingList: ShoppingList,
    onClick: (ShoppingList) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {
            onClick.invoke(shoppingList)
        },
        modifier = modifier
    ) {
        Text(
            text = shoppingList.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp
            )
        )

        Text(
            text = pluralStringResource(
                id = R.plurals.shopping_list_component_label_item_count,
                shoppingList.items.size,
                shoppingList.items.size
            ),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 2.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewShoppingListComponent() {
    AppTheme {
        ShoppingListComponent(
            shoppingList = ShoppingListMockItem,
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        )
    }
}