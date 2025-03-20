package com.example.cardapicrudpractice.domain.repository

import com.example.cardapicrudpractice.domain.model.WalletCard

/**
 * Repository interface for accessing wallet card data.
 *
 * This interface defines the contract for performing CRUD operations on wallet cards.
 */
interface CardRepository {

    /**
     * Retrieve a list of wallet cards.
     */
    suspend fun getWalletCards(): List<WalletCard>

    /**
     * Add a new wallet card.
     *
     * @param walletCard The wallet card to add.
     * @return The added wallet card with its generated ID.
     */
    suspend fun addWalletCard(walletCard: WalletCard): WalletCard

    /**
     * Updates an existing wallet card.
     *
     * @param walletCard The wallet card with updated details.
     * @return The updated wallet card.
     */
    suspend fun updateWalletCard(walletCard: WalletCard): WalletCard

    /**
     * Deletes a wallet card by its id.
     *
     * @param cardId The unique identifier of the wallet card to be deleted.
     */
    suspend fun deleteWalletCard(cardId: Int)
}