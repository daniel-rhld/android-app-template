package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_edit_shopping_list

sealed class AddEditShoppingListUiEvent {
    data class ShoppingListNameChange(val value: String) : AddEditShoppingListUiEvent()
    data object ButtonSaveClick : AddEditShoppingListUiEvent()
}