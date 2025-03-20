package com.example.cardapicrudpractice.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.cardapicrudpractice.data.FakeWalletCardApi
import com.example.cardapicrudpractice.data.CardRepositoryImpl

/**
 * Worker that refreshes wallet cards in the background.
 *
 * This worker simulates a network call to fetch updated wallet cards.
 */
class RefreshWalletCardsWorker(
    appContext: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(appContext, workerParameters) {

    /**
     * Performs the background work of fetching wallet cards.
     *
     * @return The result of the work (success or failure).
     */
    override suspend fun doWork(): Result {
        // For demonstration purposes, use the Fake API directly.
        val api = FakeWalletCardApi()
        val repository = CardRepositoryImpl(api)
        return try {
            val cards = repository.getWalletCards()
            Log.d("RefreshWorker", "Fetched ${cards.size} wallet cards in background.")
            Result.success()
        } catch (e: Exception) {
            Log.e("RefreshWorker", "Error fetching wallet cards", e)
            Result.failure()
        }
    }
}