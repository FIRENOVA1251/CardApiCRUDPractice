package com.example.cardapicrudpractice.data

import com.example.cardapicrudpractice.domain.model.WalletCard
import com.example.cardapicrudpractice.domain.repository.CardRepository

/**
 * Implementation of [CardRepository] that uses [CardApi] to perform operations.
 *
 * @property api The API instance used to fetch and manipulate wallet card data.
 */
class CardRepositoryImpl(private val api: CardApi) : CardRepository {
    override suspend fun getWalletCards(): List<WalletCard> = api.getWalletCards()
    override suspend fun addWalletCard(walletCard: WalletCard): WalletCard = api.addWalletCard(walletCard)
    override suspend fun updateWalletCard(walletCard: WalletCard): WalletCard = api.updateWalletCard(walletCard.id, walletCard)
    override suspend fun deleteWalletCard(cardId: Int) = api.deleteWalletCard(cardId)
}