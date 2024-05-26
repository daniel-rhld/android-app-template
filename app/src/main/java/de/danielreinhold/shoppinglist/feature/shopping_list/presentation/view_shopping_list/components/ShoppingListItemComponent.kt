package de.danielreinhold.shoppinglist.feature.shopping_list.presentation.view_shopping_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.danielreinhold.shoppinglist.core.presentation.theme.AppTheme
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItem
import de.danielreinhold.shoppinglist.feature.shopping_list.domain.models.ShoppingListItemMockItem

@Composable
fun ShoppingListItemComponent(
    shoppingListItem: ShoppingListItem,
    onCheckedChange: (Boolean) -> Unit,
    onButtonDecreaseCountClick: () -> Unit,
    onButtonIncreaseCountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = shoppingListItem.checked,
                onCheckedChange = onCheckedChange
            )

            Text(
                text = shoppingListItem.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground,
                textDecoration = if (shoppingListItem.checked)
                    TextDecoration.LineThrough
                else
                    TextDecoration.None
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onButtonDecreaseCountClick,
                contentPadding = PaddingValues(all = 0.dp),
                shape = RoundedCornerShape(size = 6.dp),
                modifier = Modifier.size(32.dp),
                enabled = shoppingListItem.amount > 1
            ) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            Text(
                text = shoppingListItem.amount.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Button(
                onClick = onButtonIncreaseCountClick,
                contentPadding = PaddingValues(all = 0.dp),
                shape = RoundedCornerShape(size = 6.dp),
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewShoppingListItemComponent() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            ShoppingListItemComponent(
                shoppingListItem = ShoppingListItemMockItem.copy(
                    amount = 1
                ),
                onCheckedChange = {},
                onButtonDecreaseCountClick = {},
                onButtonIncreaseCountClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ShoppingListItemComponent(
                shoppingListItem = ShoppingListItemMockItem.copy(
                    checked = false
                ),
                onCheckedChange = {},
                onButtonDecreaseCountClick = {},
                onButtonIncreaseCountClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}