package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases.FindShoppingListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val findShoppingListUseCase: FindShoppingListUseCase
) : ViewModel() {

    private var shoppingListId: Int = 0

    private val _uiState = MutableStateFlow(
        ShoppingListDetailUiState(
            shoppingList = ShoppingList(id = 0, name = "", items = listOf())
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        savedStateHandle.get<Int>("shoppingListId")?.let { shoppingListId ->
            this.shoppingListId = shoppingListId

            viewModelScope.launch {
                findShoppingListUseCase.invoke(shoppingListId = shoppingListId).collectLatest { shoppingList ->
                    if (shoppingList != null) {
                        _uiState.update {
                            it.copy(
                                shoppingList = shoppingList
                            )
                        }
                    }
                }
            }
        }
    }

}