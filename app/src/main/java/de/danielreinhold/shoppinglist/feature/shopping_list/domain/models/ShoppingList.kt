package de.danielreinhold.shoppinglist.feature.shopping_list.domain.models

data class ShoppingList(
    val id: Int,
    val name: String,
    val items: List<ShoppingListItem>
)

val ShoppingListMockItem = ShoppingList(
    id = 1,
    name = "Shopping List 1",
    items = listOf( ShoppingListItemMock, ShoppingListItemMock )
)