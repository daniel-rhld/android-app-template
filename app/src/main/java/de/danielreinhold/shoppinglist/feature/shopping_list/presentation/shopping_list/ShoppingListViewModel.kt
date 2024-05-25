package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListNameMissingException
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListNotFoundException
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases.CreateShoppingListUseCase
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases.DeleteShoppingListUseCase
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases.GetShoppingListsUseCase
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.use_cases.UpdateShoppingListUseCase
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_edit_shopping_list.AddEditShoppingListUiEvent
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_edit_shopping_list.AddEditShoppingListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val getShoppingListsUseCase: GetShoppingListsUseCase,
    private val createShoppingListUseCase: CreateShoppingListUseCase,
    private val updateShoppingListUseCase: UpdateShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        value = ShoppingListUiState(
            shoppingLists = listOf(),
            addShoppingListDialogVisible = false,
            addShoppingListUiState = AddEditShoppingListUiState(
                shoppingListId = null,
                shoppingListName = "",
                buttonSaveEnabled = false
            ),
            editShoppingListDialogVisible = false,
            editShoppingListUiState = AddEditShoppingListUiState(
                shoppingListId = null,
                shoppingListName = "",
                buttonSaveEnabled = false
            ),
            contextualShoppingList = null,
            deleteShoppingListConfirmationDialogVisible = false
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

        viewModelScope.launch {
            uiState.collectLatest {
                _uiState.update { uiState ->
                    uiState.copy(
                        editShoppingListUiState = uiState.editShoppingListUiState.copy(
                            buttonSaveEnabled = uiState.editShoppingListUiState.shoppingListName.isNotBlank()
                        )
                    )
                }
            }
        }

        viewModelScope.launch {
            getShoppingListsUseCase.invoke().collectLatest { shoppingLists ->
                _uiState.update { uiState ->
                    uiState.copy(
                        shoppingLists = shoppingLists
                    )
                }
            }
        }
    }

    fun onUiEvent(event: ShoppingListUiEvent) {
        when (event) {

            // <editor-fold desc="Add shopping list dialog events" defaultstate="collapsed">

            is ShoppingListUiEvent.ShowAddShoppingListDialog -> {
               _uiState.update {
                   it.copy(
                       addShoppingListDialogVisible = true
                   )
               }
            }

            is ShoppingListUiEvent.AddShoppingListDialogInteraction -> {
                when (event.value) {
                    is AddEditShoppingListUiEvent.ShoppingListNameChange -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                addShoppingListUiState = uiState.addShoppingListUiState.copy(
                                    shoppingListName = event.value.value
                                )
                            )
                        }
                    }
                    is AddEditShoppingListUiEvent.ButtonSaveClick -> {
                        viewModelScope.launch {
                            try {
                                createShoppingListUseCase.invoke(
                                    name = uiState.value.addShoppingListUiState.shoppingListName
                                )
                                _uiState.update { uiState ->
                                    uiState.copy(
                                        addShoppingListDialogVisible = false,
                                        addShoppingListUiState = uiState.addShoppingListUiState.copy(
                                            shoppingListName = ""
                                        )
                                    )
                                }
                            } catch (e: Exception) {
                                // TODO: Show error message
                            }
                        }
                    }
                }
            }

            is ShoppingListUiEvent.CloseAddShoppingListDialog -> {
                _uiState.update {
                    it.copy(
                        addShoppingListDialogVisible = false
                    )
                }
            }

            // </editor-fold>

            // <editor-fold desc="Edit shopping list dialog events" defaultstate="collapsed">

            is ShoppingListUiEvent.ShowEditShoppingListDialog -> {
                _uiState.update { uiState ->
                    uiState.copy(
                        editShoppingListDialogVisible = true,
                        editShoppingListUiState = uiState.editShoppingListUiState.copy(
                            shoppingListId = event.shoppingList.id,
                            shoppingListName = event.shoppingList.name
                        )
                    )
                }
            }

            is ShoppingListUiEvent.EditShoppingListDialogInteraction -> {
                when (event.value) {
                    is AddEditShoppingListUiEvent.ShoppingListNameChange -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                editShoppingListUiState = uiState.editShoppingListUiState.copy(
                                    shoppingListName = event.value.value
                                )
                            )
                        }
                    }
                    is AddEditShoppingListUiEvent.ButtonSaveClick -> {
                        viewModelScope.launch {
                            try {
                                updateShoppingListUseCase.invoke(
                                    shoppingListId = uiState.value.editShoppingListUiState.shoppingListId,
                                    name = uiState.value.editShoppingListUiState.shoppingListName
                                )
                                _uiState.update { uiState ->
                                    uiState.copy(
                                        contextualShoppingList = null,
                                        editShoppingListDialogVisible = false,
                                        editShoppingListUiState = uiState.editShoppingListUiState.copy(
                                            shoppingListName = ""
                                        )
                                    )
                                }
                            } catch (e: Exception) {
                                when (e) {
                                    is ShoppingListNotFoundException -> Unit
                                    is ShoppingListNameMissingException -> Unit
                                }
                            }
                        }
                    }
                }
            }

            is ShoppingListUiEvent.CloseEditShoppingListDialog -> {
                _uiState.update {
                    it.copy(
                        editShoppingListDialogVisible = false,
                        contextualShoppingList = null
                    )
                }
            }

            // </editor-fold>

            // <editor-fold desc="Delete shopping list events" defaultstate="collapsed">

            is ShoppingListUiEvent.ShowConfirmDeleteShoppingListDialog -> {
                _uiState.update {
                    it.copy(
                        deleteShoppingListConfirmationDialogVisible = true
                    )
                }
            }

            is ShoppingListUiEvent.CloseConfirmDeleteShoppingListDialog -> {
                _uiState.update {
                    it.copy(
                        deleteShoppingListConfirmationDialogVisible = false
                    )
                }
            }

            is ShoppingListUiEvent.DeleteShoppingList -> {
                viewModelScope.launch {
                    deleteShoppingListUseCase.invoke(event.shoppingList)

                    _uiState.update {
                        it.copy(
                            contextualShoppingList = null,
                            deleteShoppingListConfirmationDialogVisible = false
                        )
                    }
                }
            }

            // </editor-fold>

            // <editor-fold desc="Shopping list context menu events" defaultstate="collapsed">

            is ShoppingListUiEvent.ShowShoppingListContextMenu -> {
                _uiState.update {
                    it.copy(
                        contextualShoppingList = event.shoppingList
                    )
                }
            }

            is ShoppingListUiEvent.CloseShoppingListContextMenu -> {
                _uiState.update {
                    it.copy(
                        contextualShoppingList = null
                    )
                }
            }

            // </editor-fold>

            is ShoppingListUiEvent.ShowShoppingList -> {

            }
        }
    }

}