package de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item

import androidx.room.Embedded
import androidx.room.Relation
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItemTemplate

data class ShoppingListItemTemplateView(
    @Embedded
    val self: ShoppingListItemTemplateEntity,

    @Relation(
        entity = ShoppingListItemUnitEntity::class,
        parentColumn = "unit_id",
        entityColumn = "id"
    )
    val unit: ShoppingListItemUnitEntity
)

fun ShoppingListItemTemplateView.mapToDomainObject() = ShoppingListItemTemplate(
    id = self.id,
    name = self.name,
    unit = unit.mapToDomainObject()
)