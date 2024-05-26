package de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_list_item_templates",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListItemUnitEntity::class,
            parentColumns = [ "id" ],
            childColumns = [ "unit_id" ],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ShoppingListItemTemplateEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "unit_id")
    val unitId: Int,
)
