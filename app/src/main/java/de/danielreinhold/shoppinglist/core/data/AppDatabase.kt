package de.danielreinhold.shoppinglist.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list.ShoppingListEntity

@Database(
    entities = [
        ShoppingListEntity::class
   ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

}