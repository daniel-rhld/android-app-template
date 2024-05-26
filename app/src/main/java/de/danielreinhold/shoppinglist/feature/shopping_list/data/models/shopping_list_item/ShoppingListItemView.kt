package de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item

import androidx.room.Embedded
import androidx.room.Relation
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItem

data class ShoppingListItemView(
    @Embedded
    val self: ShoppingListItemEntity,

    @Relation(
        entity = ShoppingListItemUnitEntity::class,
        parentColumn = "unit_id",
        entityColumn = "id"
    )
    val unit: ShoppingListItemUnitEntity
)

fun ShoppingListItemView.mapToDomainObject() = ShoppingListItem(
    id = self.id,
    name = self.name,
    amount = self.amount,
    checked = self.checked,
    unit = unit.mapToDomainObject()
)