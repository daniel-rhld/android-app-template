package de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories

import de.danielreinhold.shoppinglist.feature.shopping_list.data.daos.ShoppingListDao
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.ShoppingListItemEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.mapToDomainModel
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingListRepositoryImpl(
    private val shoppingListDao: ShoppingListDao
) : ShoppingListRepository {
    override val shoppingLists: Flow<List<ShoppingList>>
        get() = shoppingListDao.getAllShoppingListsAsFlow().map {
            it.map {  view ->
                view.shoppingList.mapToDomainModel(
                    shoppingListItemProvider = {
                        view.shoppingListItems.map(ShoppingListItemEntity::mapToDomainModel)
                    }
                )
            }
        }

    override suspend fun findShoppingList(id: Int): ShoppingList? {
        return shoppingListDao.findShoppingList(id)?.let { view ->
            view.shoppingList.mapToDomainModel(
                shoppingListItemProvider = {
                    view.shoppingListItems.map(ShoppingListItemEntity::mapToDomainModel)
                }
            )
        }
    }

    override suspend fun upsertShoppingList(value: ShoppingListEntity) {
        shoppingListDao.upsertShoppingList(value)
    }

    override suspend fun deleteShoppingList(value: ShoppingListEntity) {
        shoppingListDao.deleteShoppingList(value)
    }
}