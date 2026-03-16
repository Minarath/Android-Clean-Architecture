# Login Demo App (Jetpack Compose + Clean Architecture)

This project is a simple **Login Demo Android Application** built using **Jetpack Compose** and **Clean Architecture principles**.
The goal of this project is to demonstrate a **scalable, modular and modern Android architecture** using **MVVM, StateFlow, SharedFlow and Retrofit API integration**.

---

# Features

* Modern UI built with **Jetpack Compose**
* **Clean Architecture implementation**
* **MVVM architecture**
* **StateFlow for UI state management**
* **SharedFlow for one-time events**
* **Snackbar error handling**
* **Loading indicator during API calls**
* **Reusable UI components**
* **Success dialog on login completion**
* **API integration using Retrofit**

---

#  Architecture

This project follows **Clean Architecture** with proper separation of concerns.

Presentation Layer
Handles UI logic and user interaction.

Domain Layer
Contains business logic and use cases.

Data Layer
Handles API calls and data sources.

#  Login Flow

```
User enters Email & Password
        ↓
Login button clicked
        ↓
ViewModel validates input
        ↓
LoginUseCase triggered
        ↓
Repository calls API
        ↓
APIResult returned
        ↓
UI updates based on state
```

---

#  Loading Handling

During API calls, the app shows a **loading indicator** using the `isLoading` state.

```
APIResult.Loading → show loader
APIResult.Success → hide loader
APIResult.Error → hide loader + show snackbar
```

---

#  Error Handling

Errors are shown using **Snackbar messages** triggered via **SharedFlow events**.

Example:

```
_eventFlow.emit(
    LoginUiEvent.ShowSnackBar("Invalid credentials")
)
```

---

# Success Handling

When login is successful, a **Success Dialog** is displayed to inform the user.

---

# Reusable Components

Reusable composables used in this project:

* `EditTextField`
* `PasswordTextField`
* `SuccessDialog`
* `LoginButton`

This helps keep UI code **modular and maintainable**.

---

# Tech Stack

* Kotlin
* Jetpack Compose
* MVVM Architecture
* Clean Architecture
* Retrofit
* Coroutines
* StateFlow
* SharedFlow

---

---

# Future Improvements

* Navigation between screens
* Unit testing
* Dependency Injection (Hilt / Koin)
* Input validation improvements
* UI animations

---

#  Author

Android Developer with experience in building modern Android applications using **Jetpack Compose, MVVM and Clean Architecture**.

---
