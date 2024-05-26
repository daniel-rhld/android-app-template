package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import de.danielreinhold.shoppinglist.core.presentation.theme.AppTheme
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListMockItem
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list.components.ShoppingListItemComponent

@Destination<RootGraph>
@Composable
fun ShoppingListDetailScreen(shoppingListId: Int) {
    val viewModel: ShoppingListDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    ShoppingListDetailScreen(
        uiState = uiState,
        onUiEvent = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListDetailScreen(
    uiState: ShoppingListDetailUiState,
    onUiEvent: (ShoppingListDetailUiEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onUiEvent.invoke(ShoppingListDetailUiEvent.NavigateUp)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(text = uiState.shoppingList.name)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onUiEvent.invoke(
                        ShoppingListDetailUiEvent.ShowAddShoppingListItemDialog
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = padding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = uiState.shoppingList.items
            ) { shoppingListItem ->
                ShoppingListItemComponent(
                    shoppingListItem = shoppingListItem,
                    onCheckedChange = { checked ->
                        onUiEvent.invoke(
                            ShoppingListDetailUiEvent.ItemCheckedChange(
                                shoppingListItem = shoppingListItem,
                                checked = checked
                            )
                        )
                    },
                    onButtonDecreaseCountClick = {
                        onUiEvent.invoke(
                            ShoppingListDetailUiEvent.DecreaseItemCount(
                                shoppingListItem = shoppingListItem
                            )
                        )
                    },
                    onButtonIncreaseCountClick = {
                        onUiEvent.invoke(
                            ShoppingListDetailUiEvent.IncreaseItemCount(
                                shoppingListItem = shoppingListItem
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewShoppingListDetailScreen() {
    AppTheme {
        ShoppingListDetailScreen(
            uiState = ShoppingListDetailUiState(
                shoppingList = ShoppingListMockItem
            ),
            onUiEvent = {}
        )
    }
}