package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import de.danielreinhold.shoppinglist.R
import de.danielreinhold.shoppinglist.core.presentation.theme.AppTheme
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItemTemplateMockItem
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListMockItem
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list.components.NotExistingShoppingListSearchResultComponent
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list.components.ShoppingListItemComponent
import de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list.components.ShoppingListSearchResultComponent

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
        containerColor = MaterialTheme.colorScheme.background,
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
                        ShoppingListDetailUiEvent.ToggleShoppingListItemSearchSection
                    )
                }
            ) {
                if (uiState.searchBarVisible) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = padding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                AnimatedVisibility(
                    visible = uiState.searchBarVisible,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BasicTextField(
                            value = uiState.searchValue,
                            onValueChange = { value ->
                                onUiEvent.invoke(
                                    ShoppingListDetailUiEvent.ShoppingListItemSearchValueChange(
                                        value = value
                                    )
                                )
                            },
                            textStyle = TextStyle(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 16.sp
                            ),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                        .padding(all = 12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        AnimatedVisibility(
                                            visible = uiState.searchValue.isNotEmpty(),
                                            enter = fadeIn() + expandHorizontally(),
                                            exit = fadeOut() + shrinkHorizontally()
                                        ) {
                                            innerTextField.invoke()
                                        }

                                        AnimatedVisibility(
                                            visible = uiState.searchValue.isEmpty(),
                                            enter = fadeIn() + expandHorizontally(),
                                            exit = fadeOut() + shrinkHorizontally()
                                        ) {
                                            Text(
                                                text = stringResource(id = R.string.shopping_list_detail_search_bar_hint),
                                                style = TextStyle(
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                                        alpha = .7F
                                                    ),
                                                    fontSize = 16.sp
                                                )
                                            )
                                        }
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp)
                        )

                        AnimatedVisibility(
                            visible = uiState.searchValue.isNotEmpty(),
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                shape = RoundedCornerShape(size = 8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    AnimatedVisibility(
                                        visible = uiState.searchResults.isNotEmpty(),
                                        enter = fadeIn() + expandVertically(),
                                        exit = fadeOut() + shrinkVertically()
                                    ) {
                                        uiState.searchResults.forEach { searchResult ->
                                            ShoppingListSearchResultComponent(
                                                searchResult = searchResult,
                                                onClick = {
                                                    onUiEvent.invoke(
                                                        ShoppingListDetailUiEvent.ShoppingListSearchItemClick(
                                                            searchItem = searchResult
                                                        )
                                                    )
                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(all = 8.dp)
                                            )
                                        }
                                    }

                                    AnimatedVisibility(
                                        visible = uiState.searchResults.isEmpty(),
                                        enter = fadeIn() + expandVertically(),
                                        exit = fadeOut() + shrinkVertically()
                                    ) {
                                        Text(
                                            text = "No results were found",
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            fontSize = 16.sp,
                                            fontStyle = FontStyle.Italic,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(all = 16.dp)
                                        )
                                    }

                                    Divider()

                                    NotExistingShoppingListSearchResultComponent(
                                        text = uiState.searchValue,
                                        onClick = {
                                            onUiEvent.invoke(
                                                ShoppingListDetailUiEvent.NotExistingShoppingListSearchItemClick(
                                                    text = uiState.searchValue
                                                )
                                            )
                                        },
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(all = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

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
                shoppingList = ShoppingListMockItem,
                searchBarVisible = true,
                searchValue = "Test",
                searchResults = listOf(
                    ShoppingListItemTemplateMockItem, ShoppingListItemTemplateMockItem
                )
            ),
            onUiEvent = {}
        )
    }
}