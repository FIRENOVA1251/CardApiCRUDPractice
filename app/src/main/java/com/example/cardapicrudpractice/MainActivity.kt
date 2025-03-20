package com.example.cardapicrudpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.cardapicrudpractice.data.FakeWalletCardApi
import com.example.cardapicrudpractice.data.CardRepositoryImpl
import com.example.cardapicrudpractice.domain.usecase.AddCardUseCase
import com.example.cardapicrudpractice.domain.usecase.DeleteCardUseCase
import com.example.cardapicrudpractice.domain.usecase.GetCardsUseCase
import com.example.cardapicrudpractice.domain.usecase.UpdateCardUseCase
import com.example.cardapicrudpractice.presentation.ui.CardDetailScreen
import com.example.cardapicrudpractice.presentation.ui.CardListScreen
import com.example.cardapicrudpractice.presentation.viewmodel.CardsViewModel
import com.example.cardapicrudpractice.presentation.viewmodel.CardsViewModelFactory
import com.example.cardapicrudpractice.ui.theme.CardApiCRUDPracticeTheme
import com.example.cardapicrudpractice.work.RefreshWalletCardsWorker
import java.util.concurrent.TimeUnit

/**
 * Main activity for the wallet app.
 *
 * Initializes dependencies, sets up background work, and configures navigation between screens.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Schedule background work to refresh wallet cards every 15 minutes.
        val workRequest = PeriodicWorkRequestBuilder<RefreshWalletCardsWorker>(15, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "RefreshWalletCards",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )

        // Initialize Fake API and Repository.
        val api = FakeWalletCardApi()
        // val api = RetrofitApi()
        val repository = CardRepositoryImpl(api)

        // Create use cases.
        val getWalletCardsUseCase = GetCardsUseCase(repository)
        val addWalletCardUseCase = AddCardUseCase(repository)
        val updateWalletCardUseCase = UpdateCardUseCase(repository)
        val deleteWalletCardUseCase = DeleteCardUseCase(repository)


        setContent {
            CardApiCRUDPracticeTheme {

                val navController = rememberNavController()

                // Create ViewModel using factory for dependency injection.
                val viewModel: CardsViewModel = viewModel(
                    factory = CardsViewModelFactory(
                        getWalletCardsUseCase,
                        addWalletCardUseCase,
                        updateWalletCardUseCase,
                        deleteWalletCardUseCase
                    )
                )

                NavHost(navController = navController, startDestination = "card_list") {
                    composable("card_list") {
                        val walletCards by viewModel.walletCards.collectAsState()
                        val isLoading by viewModel.isLoading.collectAsState()
                        CardListScreen(
                            walletCards = walletCards,
                            isLoading = isLoading,
                            onCardClick = { card ->
                                navController.navigate("card_detail/${card.id}")
                            },
                            onAddCardClick = { navController.navigate("card_detail") },
                            onDeleteCardClick = { card ->
                                viewModel.deleteWalletCard(card.id)
                            }
                        )
                    }
                    composable(
                        "card_detail/{cardId}",
                        arguments = listOf(navArgument("cardId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val cardId = backStackEntry.arguments?.getInt("cardId")
                        val walletCard =
                            viewModel.walletCards.collectAsState().value.find { it.id == cardId }
                        CardDetailScreen(
                            walletCard = walletCard,
                            onSave = { cardName, cardNumber, balance ->
                                if (walletCard != null) {
                                    viewModel.updateWalletCard(
                                        walletCard.copy(
                                            cardName = cardName,
                                            cardNumber = cardNumber,
                                            balance = balance
                                        )
                                    )
                                } else {
                                    viewModel.addWalletCard(cardName, cardNumber, balance)
                                }
                                navController.popBackStack()
                            },
                            onCancel = { navController.popBackStack() }
                        )
                    }

                    // Detail page could also add a new card when no cardId is provided.
                    composable("card_detail") {
                        CardDetailScreen(
                            walletCard = null,
                            onSave = { cardName, cardNumber, balance ->
                                viewModel.addWalletCard(cardName, cardNumber, balance)
                                navController.popBackStack()
                            },
                            onCancel = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
