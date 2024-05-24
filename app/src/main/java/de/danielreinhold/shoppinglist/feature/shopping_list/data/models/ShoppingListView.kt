package de.danielreinhold.shoppinglist.feature.shopping_list.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ShoppingListView(

    @Embedded
    val shoppingList: ShoppingListEntity,

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
    val shoppingListItems: List<ShoppingListItemEntity>

)