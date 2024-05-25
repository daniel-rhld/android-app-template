package de.danielreinhold.shoppinglist.feature.shopping_list.domain.validator

import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListNameMissingException

object ShoppingListValidator {

    fun validateShoppingList(shoppingList: ShoppingListEntity) {
        if (shoppingList.name.isBlank()) {
            throw ShoppingListNameMissingException()
        }
    }

}