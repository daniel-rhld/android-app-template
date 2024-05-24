package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_shopping_list

sealed class AddShoppingListUiEvent {
    data class ShoppingListNameChange(val value: String) : AddShoppingListUiEvent()
    data object ButtonSaveClick : AddShoppingListUiEvent()
}