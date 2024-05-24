package de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list

import de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list_item.ShoppingListItem
import de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list_item.ShoppingListItemMock

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