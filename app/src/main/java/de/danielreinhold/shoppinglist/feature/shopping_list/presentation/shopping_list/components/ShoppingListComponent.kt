package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.shopping_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.danielreinhold.shoppinglist.R
import de.danielreinhold.shoppinglist.core.presentation.theme.AppTheme
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingList
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListMockItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingListComponent(
    shoppingList: ShoppingList,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = onClick,
                onLongClick = onLongClick
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (!selected)
                MaterialTheme.colorScheme.surfaceVariant
            else
                MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = shoppingList.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (!selected)
                MaterialTheme.colorScheme.onSurface
            else
                MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp
            )
        )

        Text(
            text = pluralStringResource(
                id = R.plurals.shopping_list_component_label_item_count,
                shoppingList.items.size,
                shoppingList.items.size
            ),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = if (!selected)
                MaterialTheme.colorScheme.onSurfaceVariant
            else
                MaterialTheme.colorScheme.onPrimary.copy(.8F),
            modifier = Modifier.padding(
                start = 16.dp,
                top = 2.dp,
                end = 16.dp,
                bottom = 16.dp
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewShoppingListComponent() {
    AppTheme {
        Column(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
        ) {
            ShoppingListComponent(
                shoppingList = ShoppingListMockItem,
                onClick = {},
                onLongClick = {},
                selected = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            )

            ShoppingListComponent(
                shoppingList = ShoppingListMockItem,
                onClick = {},
                onLongClick = {},
                selected = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp,
                    )
            )
        }
    }
}