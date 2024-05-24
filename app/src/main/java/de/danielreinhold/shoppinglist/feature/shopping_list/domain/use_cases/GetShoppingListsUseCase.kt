package de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases

import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingListsUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {

    operator fun invoke(): Flow<List<ShoppingList>> = shoppingListRepository.shoppingLists

}