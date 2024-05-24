package de.danielreinhold.shoppinglist.feature.shopping_list.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.danielreinhold.shoppinglist.core.data.AppDatabase
import de.danielreinhold.shoppinglist.feature.shopping_list.data.daos.ShoppingListDao
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories.ShoppingListRepository
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.repositories.ShoppingListRepositoryImpl
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases.CreateShoppingListUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoppingListModule {

    @Singleton
    @Provides
    fun provideShoppingListDao(db: AppDatabase) = db.getShoppingListDao()

    @Singleton
    @Provides
    fun provideShoppingListRepository(
        shoppingListDao: ShoppingListDao
    ): ShoppingListRepository = ShoppingListRepositoryImpl(
        shoppingListDao = shoppingListDao
    )

    @Provides
    fun provideCreateShoppingListUseCase(
        shoppingListRepository: ShoppingListRepository
    ) = CreateShoppingListUseCase(
        shoppingListRepository = shoppingListRepository
    )

}