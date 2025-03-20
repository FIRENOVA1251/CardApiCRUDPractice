package com.example.cardapicrudpractice.domain.usecase

import com.example.cardapicrudpractice.domain.repository.CardRepository

/**
 * Use case to delete a wallet card.
 *
 * @property repository The repository used to perform wallet card operations.
 */
class DeleteCardUseCase(private val repository: CardRepository) {

    /**
     * Invokes the use case to delete a wallet card.
     *
     * @param cardId The unique identifier of the wallet card to be deleted.
     */
    suspend operator fun invoke(cardId: Int) {
        repository.deleteWalletCard(cardId)
    }
}