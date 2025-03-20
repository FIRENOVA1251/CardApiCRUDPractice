package com.example.cardapicrudpractice.data

import com.example.cardapicrudpractice.domain.model.WalletCard
import retrofit2.http.*

/**
 * API interface for wallet card CRUD operations.
 */
interface CardApi {

    /**
     * Retrieves a list of wallet cards.
     */
    @GET("walletcards")
    suspend fun getWalletCards(): List<WalletCard>

    /**
     * Adds a new wallet card.
     *
     * @param walletCard The wallet card to add.
     * @return The added wallet card.
     */
    @POST("walletcards")
    suspend fun addWalletCard(@Body walletCard: WalletCard): WalletCard

    /**
     * Updates an existing wallet card.
     *
     * @param id The id of the wallet card to update.
     * @param walletCard The wallet card with updated data.
     * @return The updated wallet card.
     */
    @PUT("walletcards/{id}")
    suspend fun updateWalletCard(@Path("id") id: Int, @Body walletCard: WalletCard): WalletCard

    /**
     * Deletes a wallet card.
     *
     * @param id The id of the wallet card to delete.
     */
    @DELETE("walletcards/{id}")
    suspend fun deleteWalletCard(@Path("id") id: Int)
}