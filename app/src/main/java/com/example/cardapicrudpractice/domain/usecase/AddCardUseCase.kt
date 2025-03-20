package com.example.cardapicrudpractice.domain.usecase

import com.example.cardapicrudpractice.domain.model.WalletCard
import com.example.cardapicrudpractice.domain.repository.CardRepository

/**
 * Use case to add a new wallet card.
 *
 * @property repository The repository used to perform wallet card operations.
 */
class AddCardUseCase(private val repository: CardRepository) {

    /**
     * Invokes the use case to add a wallet card.
     *
     * @param walletCard The wallet card to be added.
     * @return The added wallet card with a new id.
     */
    suspend operator fun invoke(walletCard: WalletCard): WalletCard {
        return repository.addWalletCard(walletCard)
    }
}