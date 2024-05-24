package de.danielreinhold.shoppinglist.feature.shopping_list.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItem
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList

@Entity(tableName = "shopping_lists")
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String
)

fun ShoppingListEntity.mapToDomainModel(shoppingListItemProvider: () -> List<ShoppingListItem>) = ShoppingList(
    id = id,
    name = name,
    items = shoppingListItemProvider.invoke()
)