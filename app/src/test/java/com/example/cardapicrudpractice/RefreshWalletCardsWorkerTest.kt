package com.example.cardapicrudpractice

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import com.example.cardapicrudpractice.work.RefreshWalletCardsWorker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class RefreshWalletCardsWorkerTest {

    @Test
    fun `doWork returns success`() = runTest {
        // Obtain the application context for testing.
        val context: Context = ApplicationProvider.getApplicationContext()

        // Build the worker using WorkManager's testing utilities.
        val worker = TestListenableWorkerBuilder<RefreshWalletCardsWorker>(context)
            .build()

        // Execute the worker's doWork method.
        val result = worker.doWork()

        // Verify that the worker returns a success result.
        assertEquals(ListenableWorker.Result.success(), result)
    }
}