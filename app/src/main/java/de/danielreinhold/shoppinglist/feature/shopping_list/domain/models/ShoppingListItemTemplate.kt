package de.danielreinhold.shoppinglist.feature.shopping_list.domain.models

class ShoppingListItemTemplate(
    val id: Int,
    val name: String,
    val unit: ShoppingListItemUnit
)

val ShoppingListItemTemplateMockItem = ShoppingListItemTemplate(
    id = 1,
    name = "Cola",
    unit = ShoppingListItemUnitMockItem
)