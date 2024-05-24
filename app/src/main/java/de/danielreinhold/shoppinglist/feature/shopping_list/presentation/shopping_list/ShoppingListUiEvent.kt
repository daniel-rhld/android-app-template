package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list

import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_shopping_list.AddShoppingListUiEvent

sealed class ShoppingListUiEvent {
    data object AddShoppingList : ShoppingListUiEvent()
    data object CloseAddShoppingListDialog : ShoppingListUiEvent()
    data class ShowShoppingList(val shoppingList: ShoppingList) : ShoppingListUiEvent()
    data class AddShoppingListDialogInteraction(val value: AddShoppingListUiEvent) : ShoppingListUiEvent()
}