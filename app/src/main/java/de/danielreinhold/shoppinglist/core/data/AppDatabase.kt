package de.danielreinhold.shoppinglist.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import de.danielreinhold.shoppinglist.feature.shopping_list.data.daos.ShoppingListDao
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListItemEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListShoppingListItemCrossRef
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList

@Database(
    entities = [
        ShoppingListEntity::class,
        ShoppingListItemEntity::class,
        ShoppingListShoppingListItemCrossRef::class
   ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getShoppingListDao(): ShoppingListDao
}