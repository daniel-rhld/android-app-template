package de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories

import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {

    val shoppingLists: Flow<List<ShoppingList>>

    fun findShoppingListAsFlow(id: Int): Flow<ShoppingList?>

    suspend fun upsertShoppingList(value: ShoppingListEntity)

    suspend fun deleteShoppingList(value: ShoppingListEntity)

}