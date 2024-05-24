package de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases

import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListNameMissingException
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories.ShoppingListRepository

class CreateShoppingListUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {

    suspend operator fun invoke(name: String) {
        if (name.isBlank()) {
            throw ShoppingListNameMissingException()
        }

        shoppingListRepository.upsertShoppingList(
            ShoppingListEntity(
                name = name
            )
        )
    }

}