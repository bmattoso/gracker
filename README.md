# Gracker - Board Game Management App

[![Instrumented tests](https://app.bitrise.io/app/034d9dae-9ecb-41d2-a02b-a3694439c181/status.svg?token=kV1BO7WyX9oqkPvpAQZBbw&branch=main)](https://app.bitrise.io/app/034d9dae-9ecb-41d2-a02b-a3694439c181)
[![codecov](https://codecov.io/gh/bmattoso/gracker/graph/badge.svg?token=Av8EVTWbbt)](https://codecov.io/gh/bmattoso/gracker)
[![CI]](https://github.com/bmattoso/gracker/actions/workflows/main_report.yml/badge.svg)

Welcome to **Gracker**, the ultimate companion app for board game enthusiasts!

Gracker is designed to enhance your board game experience by helping you keep track of your game collection, manage casual players, and record match details in one convenient place.

## Features:

1. **Game Library**: Easily build and organize your board game collection. Add details such as title, description, release year, and even upload images to create a visual catalog of your games.

2. **Casual Players**: Keep track of your friends, family, or regular gaming buddies who enjoy playing board games with you. Store their names, preferences, and skill levels for quick reference when planning game nights.

3. **Match Management**: Capture all the essential details of your board game matches. Record the date, players involved, game duration, scores, and any additional notes to create a comprehensive game history.

4. **Statistics and Insights**: Gain valuable insights into your gaming habits and performance. Gracker provides statistics such as win/loss ratios, most played games, and average game durations to help you track your progress and improve your skills.

5. **Personalized Recommendations**: Discover new board games tailored to your preferences. Gracker analyzes your gaming history and suggests similar games that you might enjoy, making it easier to expand your collection.

6. **Social Integration**: Share your gaming achievements, favorite games, and memorable match moments with your friends on social media platforms directly from the app.

Get ready to take your board gaming to the next level with Gracker! Download now and unlock a world of organization, fun, and unforgettable gaming experiences.

## Modules

The app is divided into the following modules:

![architecture draw](/assets/img/arch_android.png)

1. **App module**: This is the main entry point of the application and handles the initialization of the Jetpack Compose UI. It sets up the navigation graph and hosts the main UI screens.

2. **Core module**: The core module contains shared components, utilities, and data models used throughout the app. It provides common functionality such as network communication, data caching, and error handling.

3. **Network module**: The network module is responsible for handling all network-related operations. It encapsulates the logic for making API requests, handling responses, and managing network connectivity. This module may use libraries like Retrofit or Volley to simplify network communication.

4. **UseCase module**: The UseCase module houses the business logic of the app. It contains use case classes that orchestrate the interactions between different modules and components. Use cases handle complex operations, perform data transformations, and implement business rules.

5. **Data Source module**: The Data Source module is responsible for providing data to the app. It includes repositories, data sources, and interfaces for retrieving and persisting data. This module abstracts the data access layer and facilitates separation of concerns between the app's business logic and data management.

6. **Design System module**: The Design System module consists of reusable UI components, styles, and themes. It promotes consistent and cohesive design throughout the app. This module provides a centralized location for managing the app's visual appearance and UI guidelines.

7. **Feature modules**: The app consists of multiple feature modules, each representing a distinct feature or screen within the application. These modules are independent and encapsulated, enabling code modularity and reusability. Feature modules depend on the Core module, UseCase module, Data Source module, and Design System module as needed.


## Jetpack Compose

The app utilizes Jetpack Compose, the modern UI toolkit for Android, to build the user interface. Jetpack Compose simplifies UI development by allowing developers to describe the UI declaratively. It enables a reactive approach where the UI automatically updates in response to changes in the underlying data.

## MVVM Design Pattern

The app follows the MVVM (Model-View-ViewModel) architectural pattern, which helps separate concerns and improves testability and maintainability. The key components of the MVVM pattern in this app are:

- **Model**: Represents the data and business logic. It includes data sources, repositories, and other components responsible for managing and providing data to the ViewModel.
- **View**: Consists of Jetpack Compose UI components that display the data and handle user interactions.
- **ViewModel**: Acts as an intermediary between the View and the Model. It retrieves data from the Model and exposes it to the View. It also handles user interactions, updates the Model as needed, and triggers UI updates.

By adopting the MVVM pattern, the app achieves a clear separation of concerns, making it easier to maintain, test, and extend the application.

## Most Common Dependencies

The Android app utilizes several common dependencies to enhance functionality and streamline development. Here are the key dependencies used:

1. [Jetpack Compose](https://developer.android.com/jetpack/compose): A modern UI toolkit for Android, enabling declarative UI development.
2. [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): Part of the Jetpack library, it separates UI-related data from UI components.
3. [Retrofit](https://square.github.io/retrofit/): A popular HTTP client for Android, simplifying network requests and data retrieval.
4. [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization): Kotlin serialization consists of a compiler plugin, that generates visitor code for serializable classes, runtime library with core serialization API and support libraries with various serialization formats.
5. [Coroutine](https://kotlinlang.org/docs/coroutines-overview.html): Kotlin Coroutines for asynchronous programming, improving concurrency.
6. [Room](https://developer.android.com/jetpack/androidx/releases/room): An Android library for database management, simplifying data storage.
7. [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started): A Jetpack library for app navigation, including fragment transactions.

These dependencies play a crucial role in providing a seamless user experience, efficient network communication, and data management within the app.

## Collaboration project setup

1. Setup hooks
   ```bash
    ./config/setup.sh
   ```
2. Download the file `google-services.json` or ask for someone of the team
3. Fill the file `secrets.properties` or ask for someone of the team
4. 
