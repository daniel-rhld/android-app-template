package de.danielreinhold.shoppinglist.feature.shopping_list.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListView
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_lists")
    fun getAllShoppingListsAsFlow(): Flow<List<ShoppingListView>>

    @Query("SELECT * FROM shopping_lists WHERE id = :id LIMIT 1")
    suspend fun findShoppingList(id: Int): ShoppingListView?

    @Upsert
    suspend fun upsertShoppingList(value: ShoppingListEntity)

    @Delete
    suspend fun deleteShoppingList(value: ShoppingListEntity)

}