package de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItemUnit

@Entity(tableName = "shopping_list_item_unit")
data class ShoppingListItemUnitEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "abbreviation")
    val abbreviation: String
)

fun ShoppingListItemUnitEntity.mapToDomainObject() = ShoppingListItemUnit(
    id = id,
    name = name,
    abbreviation = abbreviation
)