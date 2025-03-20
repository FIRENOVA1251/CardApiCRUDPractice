package com.example.cardapicrudpractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardapicrudpractice.domain.model.WalletCard
import com.example.cardapicrudpractice.domain.usecase.AddCardUseCase
import com.example.cardapicrudpractice.domain.usecase.DeleteCardUseCase
import com.example.cardapicrudpractice.domain.usecase.GetCardsUseCase
import com.example.cardapicrudpractice.domain.usecase.UpdateCardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing wallet cards UI state and operations.
 *
 * @property getCardsUseCase Use case for retrieving wallet cards.
 * @property addCardUseCase Use case for adding a wallet card.
 * @property updateCardUseCase Use case for updating a wallet card.
 * @property deleteCardUseCase Use case for deleting a wallet card.
 */
class CardsViewModel(
    private val getCardsUseCase: GetCardsUseCase,
    private val addCardUseCase: AddCardUseCase,
    private val updateCardUseCase: UpdateCardUseCase,
    private val deleteCardUseCase: DeleteCardUseCase
) : ViewModel() {


    private val _walletCards = MutableStateFlow<List<WalletCard>>(emptyList())
    // Current list or cards.
    val walletCards: StateFlow<List<WalletCard>> = _walletCards

    private val _isLoading = MutableStateFlow(false)
    // Loading state indicating background operations.
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchWalletCards()
    }

    /**
     * Fetches wallet cards using the [GetCardsUseCase].
     */
    fun fetchWalletCards() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _walletCards.value = getCardsUseCase.invoke()
            } catch (e: Exception) {
                // Error handling
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Adds a new wallet card using the [AddCardUseCase].
     *
     * @param cardName The name of the wallet card.
     * @param cardNumber The card number.
     * @param balance The card balance.
     */
    fun addWalletCard(cardName: String, cardNumber: String, balance: Double) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newCard = addCardUseCase.invoke(
                    WalletCard(id = 0, cardName = cardName, cardNumber = cardNumber, balance = balance)
                )
                _walletCards.value = _walletCards.value + newCard
            } catch (e: Exception) {
                // Error handling
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Updates an existing wallet card using the [UpdateCardUseCase].
     *
     * @param walletCard The wallet card with updated details.
     */
    fun updateWalletCard(walletCard: WalletCard) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val updatedCard = updateCardUseCase.invoke(walletCard)
                _walletCards.value = _walletCards.value.map { if (it.id == updatedCard.id) updatedCard else it }
            } catch (e: Exception) {
                // Error handling
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Deletes a wallet card using the [DeleteCardUseCase].
     *
     * @param cardId The unique identifier of the wallet card to delete.
     */
    fun deleteWalletCard(cardId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                deleteCardUseCase.invoke(cardId)
                _walletCards.value = _walletCards.value.filterNot { it.id == cardId }
            } catch (e: Exception) {
                // Error handling
            } finally {
                _isLoading.value = false
            }
        }
    }
}