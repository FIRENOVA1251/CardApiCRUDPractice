# Wallet App with Clean Architecture

This project is a simple Wallet App built using Kotlin, Jetpack Compose, and MVVM architecture. It demonstrates a Clean Architecture approach by separating the code into Domain, Data, and Presentation layers. The app supports basic CRUD operations via API integration (using a fake API for development), handles user inputs, provides navigation between screens, and executes simple background tasks with WorkManager.


## Features

- **User Input Handling:**  
  Allows users to interact with the app to add, edit, or delete wallet cards.

- **API Integration (CRUD Operations):**  
  Interacts with a backend system to perform create, read, update, and delete operations. A Fake API is used during development with a placeholder for a Real API interface.

- **Navigation:**  
  Uses Jetpack Compose Navigation to move between the card list screen and card detail screen.

- **Background Tasks:**  
  Utilizes WorkManager to periodically refresh wallet card data in the background.

- **MVVM Architecture:**  
  Implements the MVVM pattern using Coroutines, Retrofit, Repository, and StateFlow for clear separation of concerns.

- **Clean Architecture:**  
  Separates the codebase into Domain, Data, and Presentation layers for better testability and maintainability.

## Architecture Overview

- **Domain Layer:**  
  - **Entity:** `WalletCard` – Represents a wallet card (card name, number, balance).  
  - **Repository Interface:** Defines the operations for wallet cards (CRUD).  
  - **Use Cases:** Encapsulate business logic for getting, adding, updating, and deleting wallet cards.

- **Data Layer:**  
  - **API Interfaces:**  
    - `WalletCardApi` – Defines endpoints for CRUD operations.  
    - `FakeWalletCardApi` – A simulated API implementation for development.  
    - `RealWalletCardApi` – A placeholder for real API integration (currently using a fake base URL).  
  - **Repository Implementation:** Implements the repository interface by interacting with the API.

- **Presentation Layer:**  
  - **ViewModel:**  
    - `CardsViewModel` – Manages UI state and interactions by invoking use cases.
    - **Factory:** `CardsViewModelFactory` provides dependencies to the ViewModel.
  - **UI Components:**  
    - **CardListScreen:** Displays a list of wallet cards.
    - **CardDetailScreen:** Handles adding/editing wallet cards.
  - **Navigation:** Uses Compose Navigation to transition between screens.

- **Background Tasks:**  
  - **WorkManager:**  
    - `RefreshWalletCardsWorker` periodically fetches wallet cards in the background.

## Getting Started

### Prerequisites

- **Android Studio:** Version Arctic Fox or newer.
- **Android SDK:** Minimum SDK 21, compile SDK 35 recommended.
- **Kotlin:** Version 1.8 or newer.
- **Android Gradle Plugin:** Version 8.3.1 (or compatible version).

### Setup

1. **Clone the Repository and Open in Android Studio:**

  - Open Android Studio and select File > Open, then navigate to the project folder.
  
2. **Sync Gradle:**

  - Let Android Studio download all dependencies.

3. **Build the Project:**

  - Use Build > Make Project in Android Studio.

4. **Run on Emulator/Device:**

  - Select a target device/emulator and click Run.


## Design Decisions
- Clean Architecture:
  - The project is organized into Domain, Data, and Presentation layers to ensure separation of concerns. This makes the code more maintainable and testable.

- MVVM Pattern:
  - The use of ViewModel and StateFlow helps manage UI state effectively and decouples UI logic from business logic.

- API Integration:
  - A Fake API is implemented for development purposes. A Real API interface is also defined with a placeholder base URL, facilitating easy future integration.

- Background Processing:
  - WorkManager is used to simulate background data refresh tasks. This can later be expanded to include real-time data synchronization or notifications.

## Future Improvements
- Real API Integration:
  - Replace the Fake API with a real backend service. Update the base URL and implement proper network error handling.

- Enhanced Error Handling:
  - Provide detailed user feedback for network errors, validation issues, and background task failures.

- Dependency Injection:
  - Use Hilt or another DI framework to manage dependencies more effectively, reducing boilerplate code.

- UI/UX Enhancements:
  - Improve the user interface with better designs, animations, and accessibility improvements.

- Comprehensive Testing:
  - Expand unit tests to cover additional scenarios, integration tests, and UI tests.

## Dependencies
- AndroidX Libraries: Jetpack Compose, Lifecycle, Navigation, WorkManager.
- Kotlin Coroutines: For asynchronous operations.
- Retrofit & Gson: For API integration.
- Mockito: For unit testing and mocking.
- Robolectric: For simulating Android environment in tests.
- Kotlinx-Coroutines-Test: For testing coroutine-based code.
