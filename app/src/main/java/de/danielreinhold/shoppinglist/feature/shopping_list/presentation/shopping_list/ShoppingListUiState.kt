package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list

import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_edit_shopping_list.AddEditShoppingListUiState

data class ShoppingListUiState(
    val shoppingLists: List<ShoppingList>,

    val addShoppingListDialogVisible: Boolean,
    val addShoppingListUiState: AddEditShoppingListUiState,

    val editShoppingListDialogVisible: Boolean,
    val editShoppingListUiState: AddEditShoppingListUiState,

    val contextualShoppingList: ShoppingList?,

    val deleteShoppingListConfirmationDialogVisible: Boolean
)