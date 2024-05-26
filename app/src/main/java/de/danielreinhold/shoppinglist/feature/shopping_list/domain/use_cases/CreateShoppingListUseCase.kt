package de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases

import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories.ShoppingListRepository
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.validator.ShoppingListValidator

class CreateShoppingListUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {

    suspend operator fun invoke(name: String) {
        val shoppingList = ShoppingListEntity(
            name = name
        )

        ShoppingListValidator.validateShoppingList(shoppingList)
        shoppingListRepository.upsertShoppingList(shoppingList)
    }

}