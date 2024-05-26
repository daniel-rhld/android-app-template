package de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item.ShoppingListItemEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item.ShoppingListItemView
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item.mapToDomainObject
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList

data class ShoppingListView(

    @Embedded
    val self: ShoppingListEntity,

    @Relation(
        entity = ShoppingListItemEntity::class,
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ShoppingListShoppingListItemCrossRef::class,
            parentColumn = "shopping_list_id",
            entityColumn = "shopping_list_item_id"
        )
    )
    val shoppingListItems: List<ShoppingListItemView>
)

fun ShoppingListView.mapToDomainObject() = ShoppingList(
    id = self.id,
    name = self.name,
    items = shoppingListItems.map(ShoppingListItemView::mapToDomainObject)
)