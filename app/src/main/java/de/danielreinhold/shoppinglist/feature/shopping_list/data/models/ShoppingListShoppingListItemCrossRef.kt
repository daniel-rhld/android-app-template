package de.danielreinhold.shoppinglist.feature.shopping_list.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList

@Entity(
    tableName = "shopping_list_shopping_list_item",
    primaryKeys = [ "shopping_list_id", "shopping_list_item_id" ],
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = [ "id" ],
            childColumns = [ "shopping_list_id" ],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ShoppingListItemEntity::class,
            parentColumns = [ "id" ],
            childColumns = [ "shopping_list_item_id" ],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class ShoppingListShoppingListItemCrossRef(
    @ColumnInfo(name = "shopping_list_id")
    val shoppingListId: Int,

    @ColumnInfo(name = "shopping_list_item_id")
    val shoppingListItemId: Int
)
