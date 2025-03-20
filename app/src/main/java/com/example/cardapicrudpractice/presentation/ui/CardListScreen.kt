package com.example.cardapicrudpractice.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cardapicrudpractice.domain.model.WalletCard


/**
 * Displays a list of wallet cards.
 *
 * @param walletCards The list of wallet cards to display.
 * @param isLoading Indicates whether data is currently loading.
 * @param onCardClick Callback when a card is clicked.
 * @param onAddCardClick Callback when the add card button is clicked.
 * @param onDeleteCardClick Callback when a card delete action is triggered.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(
    walletCards: List<WalletCard>,
    isLoading: Boolean,
    onCardClick: (WalletCard) -> Unit,
    onAddCardClick: () -> Unit,
    onDeleteCardClick: (WalletCard) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Wallet Cards") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddCardClick) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(walletCards) { card ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { onCardClick(card) },
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = card.cardName,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "**** **** **** " +card.cardNumber.takeLast(4)

                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = "Balance: \$${card.balance}")
                                }
                                IconButton(onClick = { onDeleteCardClick(card) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}