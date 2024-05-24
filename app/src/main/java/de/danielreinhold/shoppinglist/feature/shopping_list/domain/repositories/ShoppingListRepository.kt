package de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories

import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {

    val shoppingLists: Flow<List<ShoppingList>>

    suspend fun findShoppingList(id: Int): ShoppingList?

    suspend fun upsertShoppingList(value: ShoppingListEntity)

    suspend fun deleteShoppingList(value: ShoppingListEntity)

}