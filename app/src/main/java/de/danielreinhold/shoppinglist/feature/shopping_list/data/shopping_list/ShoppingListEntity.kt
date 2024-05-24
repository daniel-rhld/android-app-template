package de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list_item.ShoppingListItem
import de.danielreinhold.shoppinglist.feature.shopping_list.data.shopping_list_item.ShoppingListItemEntity

@Entity(tableName = "shopping_lists")
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String
)

fun ShoppingListEntity.mapToDomainModel(itemProvider: () -> List<ShoppingListItem>) = ShoppingList(
    id = id,
    name = name,
    items = itemProvider.invoke()
)