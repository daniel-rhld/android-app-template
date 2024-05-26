package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list

import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItem
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItemTemplate

sealed class ShoppingListDetailUiEvent {
    data object NavigateUp : ShoppingListDetailUiEvent()

    data class IncreaseItemCount(
        val shoppingListItem: ShoppingListItem
    ) : ShoppingListDetailUiEvent()
    data class DecreaseItemCount(
        val shoppingListItem: ShoppingListItem
    ) : ShoppingListDetailUiEvent()
    data class ItemCheckedChange(
        val shoppingListItem: ShoppingListItem,
        val checked: Boolean
    ) : ShoppingListDetailUiEvent()

    data object ToggleShoppingListItemSearchSection : ShoppingListDetailUiEvent()
    data class ShoppingListItemSearchValueChange(val value: String) : ShoppingListDetailUiEvent()
    data class ShoppingListSearchItemClick(
        val searchItem: ShoppingListItemTemplate
    ) : ShoppingListDetailUiEvent()
    data class NotExistingShoppingListSearchItemClick(
        val text: String
    ) : ShoppingListDetailUiEvent()
}