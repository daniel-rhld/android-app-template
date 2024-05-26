package de.danielreinhold.shoppinglist.feature.shopping_list.domain.models

data class ShoppingListItemUnit(
    val id: Int,
    val name: String,
    val abbreviation: String
)

val ShoppingListItemUnitMockItem = ShoppingListItemUnit(
    id = 1,
    name = "Kilogramm",
    abbreviation = "Kg"
)