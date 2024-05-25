package de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases

import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.toEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories.ShoppingListRepository
import javax.inject.Inject


/**
 * DeleteShoppingListUseCase
 */
class DeleteShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {

    suspend operator fun invoke(shoppingList: ShoppingList) {
        shoppingListRepository.deleteShoppingList(shoppingList.toEntity())
    }

}