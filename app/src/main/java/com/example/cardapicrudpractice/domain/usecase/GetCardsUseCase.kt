package com.example.cardapicrudpractice.domain.usecase

import com.example.cardapicrudpractice.domain.model.WalletCard
import com.example.cardapicrudpractice.domain.repository.CardRepository

/**
 * Use case to retrieve the list of wallet cards.
 *
 * @property repository The repository used to fetch wallet card data.
 */
class GetCardsUseCase(private val repository: CardRepository) {
    suspend operator fun invoke(): List<WalletCard> {
        return repository.getWalletCards()
    }
}