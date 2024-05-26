package de.danielreinhold.shoppinglist.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import de.danielreinhold.shoppinglist.feature.shopping_list.data.daos.ShoppingListDao
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item.ShoppingListItemEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list.ShoppingListShoppingListItemCrossRef
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item.ShoppingListItemTemplateEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item.ShoppingListItemUnitEntity

@Database(
    entities = [
        ShoppingListEntity::class,
        ShoppingListItemEntity::class,
        ShoppingListShoppingListItemCrossRef::class,
        ShoppingListItemTemplateEntity::class,
        ShoppingListItemUnitEntity::class
   ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getShoppingListDao(): ShoppingListDao
}