package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list

import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_edit_shopping_list.AddEditShoppingListUiEvent
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_edit_shopping_list.AddEditShoppingListUiState

sealed class ShoppingListUiEvent {
    data object AddShoppingList : ShoppingListUiEvent()
    data object CloseAddShoppingListDialog : ShoppingListUiEvent()
    data object CloseEditShoppingListDialog : ShoppingListUiEvent()
    data class ShowShoppingList(val shoppingList: ShoppingList) : ShoppingListUiEvent()
    data class ShowShoppingListContextMenu(val shoppingList: ShoppingList) : ShoppingListUiEvent()
    data object CloseShoppingListContextMenu : ShoppingListUiEvent()
    data class EditShoppingList(val shoppingList: ShoppingList) : ShoppingListUiEvent()
    data class DeleteShoppingList(val shoppingList: ShoppingList) : ShoppingListUiEvent()
    data class AddShoppingListDialogInteraction(val value: AddEditShoppingListUiEvent) : ShoppingListUiEvent()
    data class EditShoppingListDialogInteraction(val value: AddEditShoppingListUiEvent) : ShoppingListUiEvent()
}