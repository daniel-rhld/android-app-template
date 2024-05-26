package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list

import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItemTemplate

data class ShoppingListDetailUiState(
    val shoppingList: ShoppingList,
    val searchBarVisible: Boolean,
    val searchValue: String,
    val searchResults: List<ShoppingListItemTemplate>
)