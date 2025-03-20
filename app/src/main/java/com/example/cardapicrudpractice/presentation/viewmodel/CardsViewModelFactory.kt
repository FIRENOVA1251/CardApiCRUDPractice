package com.example.cardapicrudpractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardapicrudpractice.domain.usecase.AddCardUseCase
import com.example.cardapicrudpractice.domain.usecase.DeleteCardUseCase
import com.example.cardapicrudpractice.domain.usecase.GetCardsUseCase
import com.example.cardapicrudpractice.domain.usecase.UpdateCardUseCase


/**
 * Factory class for creating [CardsViewModel] instances.
 *
 * @property getWalletCardsUseCase Use case for retrieving wallet cards.
 * @property addWalletCardUseCase Use case for adding a wallet card.
 * @property updateWalletCardUseCase Use case for updating a wallet card.
 * @property deleteWalletCardUseCase Use case for deleting a wallet card.
 */
class CardsViewModelFactory(
    private val getWalletCardsUseCase: GetCardsUseCase,
    private val addWalletCardUseCase: AddCardUseCase,
    private val updateWalletCardUseCase: UpdateCardUseCase,
    private val deleteWalletCardUseCase: DeleteCardUseCase
) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of [CardsViewModel] with the required use cases.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardsViewModel::class.java)) {
            return CardsViewModel(
                getWalletCardsUseCase,
                addWalletCardUseCase,
                updateWalletCardUseCase,
                deleteWalletCardUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}