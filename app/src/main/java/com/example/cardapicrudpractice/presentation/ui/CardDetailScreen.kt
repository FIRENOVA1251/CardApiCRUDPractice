package com.example.cardapicrudpractice.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.cardapicrudpractice.domain.model.WalletCard

/**
 * Screen for displaying and editing wallet card details.
 *
 * If [walletCard] is null, the screen functions as an "add card" screen.
 *
 * @param walletCard The wallet card to edit, or null if adding a new card.
 * @param onSave Callback invoked when saving the card.
 * @param onCancel Callback invoked when canceling the operation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailScreen(
    walletCard: WalletCard? = null,
    onSave: (cardName: String, cardNumber: String, balance: Double) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current
    var cardName by remember { mutableStateOf(walletCard?.cardName ?: "") }
    var cardNumber by remember { mutableStateOf((TextFieldValue(walletCard?.cardNumber ?: ""))) }
    var balanceText by remember { mutableStateOf(walletCard?.balance?.toString() ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (walletCard == null) "Add Card" else "Edit Card") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = cardName,
                onValueChange = { cardName = it },
                label = { Text("Card Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = cardNumber,
                onValueChange = { newValue ->

                    val newText = newValue.text
                    // Only 16 numbers.
                    val digits = newValue.text.filter { char -> char.isDigit() }.take(16)
                    // Add space within every 4 digits.
                    val formattedText =
                        digits.chunked(4).joinToString(" ")

                    // Calculate cursor position.
                    var newCursorPos = newValue.selection.start
                    if (newText.length % 5 == 0) {
                        newCursorPos = newValue.selection.start + 1
                    }

                    // Update state
                    cardNumber = TextFieldValue(
                        text = formattedText,
                        selection = TextRange(newCursorPos)
                    )
                },
                label = { Text("Card Number") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = balanceText,
                onValueChange = { balanceText = it },
                label = { Text("Balance") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = onCancel) {
                    Text("Cancel")
                }
                Button(onClick = {

                    if (cardName != "" && cardNumber.text.length == 19) {
                        val balance = balanceText.toDoubleOrNull() ?: 0.0

                        onSave(cardName, cardNumber.text, balance)
                    } else {
                        Toast.makeText(context, "invalid input", Toast.LENGTH_SHORT).show()
                    }

                }) {
                    Text("Save")
                }
            }
        }
    }
}