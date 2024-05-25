package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_edit_shopping_list

data class AddEditShoppingListUiState(
    val shoppingListId: Int?,
    val shoppingListName: String,
    val buttonSaveEnabled: Boolean
)
