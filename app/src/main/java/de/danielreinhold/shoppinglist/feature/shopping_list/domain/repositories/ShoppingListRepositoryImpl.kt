package de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories

import de.danielreinhold.shoppinglist.feature.shopping_list.data.daos.ShoppingListDao
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list.ShoppingListEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list.mapToDomainObject
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item.ShoppingListItemEntity
import de.danielreinhold.shoppinglist.feature.shopping_list.data.models.shopping_list_item.mapToDomainObject
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShoppingListRepositoryImpl(
    private val shoppingListDao: ShoppingListDao
) : ShoppingListRepository {
    override val shoppingLists: Flow<List<ShoppingList>>
        get() = shoppingListDao.getAllShoppingListsAsFlow().map {
            it.map { view -> view.mapToDomainObject() }
        }

    override fun findShoppingListAsFlow(id: Int): Flow<ShoppingList?> {
        return shoppingListDao.findShoppingListAsFlow(id).let { flow ->
            flow.map { view -> view?.mapToDomainObject() }
        }
    }

    override suspend fun upsertShoppingList(value: ShoppingListEntity) {
        shoppingListDao.upsertShoppingList(value)
    }

    override suspend fun deleteShoppingList(value: ShoppingListEntity) {
        shoppingListDao.deleteShoppingList(value)
    }
}