package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list

import de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_shopping_list.AddShoppingListUiState

data class ShoppingListUiState(
    val shoppingLists: List<ShoppingList>,
    val addShoppingListDialogVisible: Boolean,
    val addShoppingListUiState: AddShoppingListUiState
)