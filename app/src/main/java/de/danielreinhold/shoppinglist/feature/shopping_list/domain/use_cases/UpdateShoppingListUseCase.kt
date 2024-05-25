package de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases

import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListNotFoundException
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories.ShoppingListRepository
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.validator.ShoppingListValidator
import javax.inject.Inject

class UpdateShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {

    suspend operator fun invoke(shoppingListId: Int?, name: String) {
        if (shoppingListId == null) {
            throw ShoppingListNotFoundException()
        }

        val shoppingList = ShoppingListEntity(
            id = shoppingListId,
            name = name
        )

        ShoppingListValidator.validateShoppingList(shoppingList)
        shoppingListRepository.upsertShoppingList(shoppingList)
    }

}