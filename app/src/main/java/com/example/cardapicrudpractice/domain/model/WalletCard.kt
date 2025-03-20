package com.example.cardapicrudpractice.domain.model

/**
 * Data class representing a wallet card.
 *
 * @property id Unique identifier for the wallet card.
 * @property cardName The name of the card.
 * @property cardNumber The card number (masked or full).
 * @property balance The current balance on the card.
 */
data class WalletCard(
    val id: Int,
    val cardName: String,
    val cardNumber: String,
    val balance: Double
)