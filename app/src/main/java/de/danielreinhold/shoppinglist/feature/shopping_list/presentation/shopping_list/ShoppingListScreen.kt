package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import de.danielreinhold.shoppinglist.R
import de.danielreinhold.shoppinglist.core.presentation.theme.AppTheme
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListMockItem
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_shopping_list.AddShoppingListScreen
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.add_shopping_list.AddShoppingListUiState
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list.components.EmptyShoppingListComponent
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list.components.ShoppingListComponent

@Destination<RootGraph>(start = true)
@Composable
fun ShoppingListScreen() {
    val viewModel: ShoppingListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    ShoppingListScreen(
        uiState = uiState,
        onUiEvent = viewModel::onUiEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShoppingListScreen(
    uiState: ShoppingListUiState,
    onUiEvent: (ShoppingListUiEvent) -> Unit
) {
    if (uiState.addShoppingListDialogVisible) {
        ModalBottomSheet(
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            ),
            onDismissRequest = {
                onUiEvent.invoke(ShoppingListUiEvent.CloseAddShoppingListDialog)
            },
            windowInsets = WindowInsets(0.dp),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            AddShoppingListScreen(
                uiState = uiState.addShoppingListUiState,
                onUiEvent = { event ->
                    onUiEvent.invoke(
                        ShoppingListUiEvent.AddShoppingListDialogInteraction(value = event)
                    )
                },
                modifier = Modifier.padding(all = 16.dp)
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    AnimatedVisibility(
                        visible = uiState.contextualShoppingList == null,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally()
                    ) {
                        Box(
                            modifier = Modifier.height(40.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(text = stringResource(id = R.string.shopping_list_toolbar_title))
                        }
                    }

                    AnimatedVisibility(
                        visible = uiState.contextualShoppingList != null,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally()
                    ) {
                        Row {
                            IconButton(
                                onClick = {
                                    uiState.contextualShoppingList?.let { shoppingList ->
                                        onUiEvent.invoke(
                                            ShoppingListUiEvent.EditShoppingList(shoppingList = shoppingList)
                                        )
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Edit,
                                    contentDescription = null
                                )
                            }

                            IconButton(
                                onClick = {
                                    uiState.contextualShoppingList?.let { shoppingList ->
                                        onUiEvent.invoke(
                                            ShoppingListUiEvent.DeleteShoppingList(shoppingList = shoppingList)
                                        )
                                    }
                                },
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = MaterialTheme.colorScheme.error
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Delete,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                },
                actions = {
                    AnimatedVisibility(
                        visible = uiState.contextualShoppingList != null,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally()
                    ) {
                        IconButton(
                            onClick = {
                                onUiEvent.invoke(
                                    ShoppingListUiEvent.CloseShoppingListContextMenu
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            if (uiState.shoppingLists.isNotEmpty()) {
                FloatingActionButton(
                    onClick = {
                        onUiEvent.invoke(ShoppingListUiEvent.AddShoppingList)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null
                    )
                }
            }
        }
    ) { padding ->
        if (uiState.shoppingLists.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(paddingValues = padding),
                contentPadding = PaddingValues(
                    all = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = uiState.shoppingLists
                ) { shoppingList ->
                    ShoppingListComponent(
                        shoppingList = shoppingList,
                        onClick = {
                            onUiEvent.invoke(
                                ShoppingListUiEvent.ShowShoppingList(shoppingList = shoppingList)
                            )
                        },
                        onLongClick = {
                            onUiEvent.invoke(
                                ShoppingListUiEvent.ShowShoppingListContextMenu(shoppingList = shoppingList)
                            )
                        },
                        selected = shoppingList.id == uiState.contextualShoppingList?.id,
                        modifier = Modifier.fillMaxWidth().animateItem()
                    )
                }
            }
        }

        if (uiState.shoppingLists.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = padding),
                contentAlignment = Alignment.Center
            ) {
                EmptyShoppingListComponent(
                    onButtonAddShoppingListClick = {
                        onUiEvent.invoke(ShoppingListUiEvent.AddShoppingList)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewShoppingListScreen() {
    AppTheme {
        ShoppingListScreen(
            uiState = ShoppingListUiState(
                shoppingLists = listOf(ShoppingListMockItem, ShoppingListMockItem),
                addShoppingListDialogVisible = false,
                addShoppingListUiState = AddShoppingListUiState(
                    shoppingListName = "",
                    buttonSaveEnabled = false
                ),
                contextualShoppingList = ShoppingListMockItem
            ),
            onUiEvent = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewEmptyShoppingListScreen() {
    AppTheme {
        ShoppingListScreen(
            uiState = ShoppingListUiState(
                shoppingLists = listOf(),
                addShoppingListDialogVisible = false,
                addShoppingListUiState = AddShoppingListUiState(
                    shoppingListName = "",
                    buttonSaveEnabled = false
                ),
                contextualShoppingList = null
            ),
            onUiEvent = {}
        )
    }
}