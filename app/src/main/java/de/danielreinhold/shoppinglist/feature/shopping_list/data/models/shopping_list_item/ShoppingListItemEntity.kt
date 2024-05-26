package de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItem

@Entity(
    tableName = "shopping_list_items",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListItemUnitEntity::class,
            parentColumns = [ "id" ],
            childColumns = [ "unit_id" ],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ShoppingListItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "checked")
    val checked: Boolean,

    @ColumnInfo(name = "unit_id")
    val unitId: Int,
)