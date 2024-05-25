package de.danielreinhold.shoppinglist.feature.shopping_list.domain.models

import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListEntity

data class ShoppingList(
    val id: Int,
    val name: String,
    val items: List<ShoppingListItem>
)

fun ShoppingList.toEntity() = ShoppingListEntity(
    id = id,
    name = name
)

val ShoppingListMockItem = ShoppingList(
    id = 1,
    name = "Shopping List 1",
    items = listOf( ShoppingListItemMock, ShoppingListItemMock )
)