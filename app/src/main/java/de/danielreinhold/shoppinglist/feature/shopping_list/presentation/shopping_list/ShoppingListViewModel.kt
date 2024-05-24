package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_shopping_list.AddShoppingListUiEvent
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_shopping_list.AddShoppingListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        value = ShoppingListUiState(
            shoppingLists = listOf(),
            addShoppingListDialogVisible = false,
            addShoppingListUiState = AddShoppingListUiState(
                shoppingListName = "",
                buttonSaveEnabled = false
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            uiState.collectLatest {
                _uiState.update { uiState ->
                    uiState.copy(
                        addShoppingListUiState = uiState.addShoppingListUiState.copy(
                            buttonSaveEnabled = uiState.addShoppingListUiState.shoppingListName.isNotBlank()
                        )
                    )
                }
            }
        }
    }

    fun onUiEvent(event: ShoppingListUiEvent) {
        when (event) {
            is ShoppingListUiEvent.AddShoppingList -> {
               _uiState.update {
                   it.copy(
                       addShoppingListDialogVisible = true
                   )
               }
            }

            is ShoppingListUiEvent.CloseAddShoppingListDialog -> {
                _uiState.update {
                    it.copy(
                        addShoppingListDialogVisible = false
                    )
                }
            }

            is ShoppingListUiEvent.AddShoppingListDialogInteraction -> {
                when (event.value) {
                    is AddShoppingListUiEvent.ShoppingListNameChange -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                addShoppingListUiState = uiState.addShoppingListUiState.copy(
                                    shoppingListName = event.value.value
                                )
                            )
                        }
                    }
                    is AddShoppingListUiEvent.ButtonSaveClick -> {
                        // TODO: Save
                    }
                }
            }

            is ShoppingListUiEvent.ShowShoppingList -> {

            }
        }
    }

}