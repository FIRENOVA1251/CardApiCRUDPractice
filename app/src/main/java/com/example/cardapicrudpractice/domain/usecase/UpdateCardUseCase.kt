package com.example.cardapicrudpractice.domain.usecase

import com.example.cardapicrudpractice.domain.model.WalletCard
import com.example.cardapicrudpractice.domain.repository.CardRepository

/**
 * Use case to update an existing wallet card.
 *
 * @property repository The repository used to perform wallet card operations.
 */
class UpdateCardUseCase(private val repository: CardRepository) {

    /**
     * Invokes the use case to update a wallet card.
     *
     * @param walletCard The wallet card with updated information.
     * @return The updated wallet card.
     */
    suspend operator fun invoke(walletCard: WalletCard): WalletCard {
        return repository.updateWalletCard(walletCard)
    }
}