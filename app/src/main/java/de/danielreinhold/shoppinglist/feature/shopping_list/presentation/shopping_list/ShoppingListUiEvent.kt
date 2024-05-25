package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list

import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_edit_shopping_list.AddEditShoppingListUiEvent

sealed class ShoppingListUiEvent {

    // <editor-fold desc="Add shopping list dialog events" defaultstate="collapsed">

    data object ShowAddShoppingListDialog : ShoppingListUiEvent()
    data class AddShoppingListDialogInteraction(val value: AddEditShoppingListUiEvent) : ShoppingListUiEvent()
    data object CloseAddShoppingListDialog : ShoppingListUiEvent()

    // </editor-fold>

    // <editor-fold desc="Edit shopping list dialog events" defaultstate="collapsed">

    data class ShowEditShoppingListDialog(val shoppingList: ShoppingList) : ShoppingListUiEvent()
    data class EditShoppingListDialogInteraction(val value: AddEditShoppingListUiEvent) : ShoppingListUiEvent()
    data object CloseEditShoppingListDialog : ShoppingListUiEvent()

    // </editor-fold>

    // <editor-fold desc="Delete shopping list events" defaultstate="collapsed">

    data object ShowConfirmDeleteShoppingListDialog : ShoppingListUiEvent()
    data object CloseConfirmDeleteShoppingListDialog : ShoppingListUiEvent()
    data class DeleteShoppingList(val shoppingList: ShoppingList) : ShoppingListUiEvent()

    // </editor-fold>

    // <editor-fold desc="Shopping list context menu events" defaultstate="collapsed">

    data class ShowShoppingListContextMenu(val shoppingList: ShoppingList) : ShoppingListUiEvent()
    data object CloseShoppingListContextMenu : ShoppingListUiEvent()

    // </editor-fold>

    data class ShowShoppingList(val shoppingList: ShoppingList) : ShoppingListUiEvent()

}