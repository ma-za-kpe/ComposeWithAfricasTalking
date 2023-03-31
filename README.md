# ComposeWithAfricasTalking
- A simple project demonstrating the use of some of the Africa's talking API's in a 
jetpack compose way. This project is built with scalability and expansion in mind following the [Guide to app architecture](https://developer.android.com/topic/architecture).

## Screenshots
| #Light Mode    | #2 Dark Mode   |
| :---: | :---: |
| ![Screenshot_20230326_173918](https://user-images.githubusercontent.com/28810111/227786216-c562c549-3b6f-468c-89df-40f1481fcd6b.png)   | ![Screenshot_20230326_174015](https://user-images.githubusercontent.com/28810111/227786209-ba4c105c-a907-4346-865e-d783982279fe.png)   |

## Features

### 🖼️ UI
      
The app contains simple Compose forms that that let users purchase airtime

### 📚 API Services
* [Africas Talking Airtime](https://developers.africastalking.com/docs/airtime/sending)
      
### 🧱 Build

* [Groovygradle files](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
* [Version catalog](https://docs.gradle.org/current/userguide/platforms.html)

### 🏠 Architecture

* [Room Database](https://developer.android.com/training/data-storage/room)
* Dependency injection with [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Jetpack ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* UI using [Jetpack Compose](https://developer.android.com/jetpack/compose) and
[Material3](https://developer.android.com/jetpack/androidx/releases/compose-material3)
* [Jetpack Navigation](https://developer.android.com/jetpack/compose/navigation)
* [Reactive data layer](https://developer.android.com/topic/architecture/data-layer)
* [Kotlin Coroutines and Flow](https://developer.android.com/kotlin/coroutines)
* [Unit tests](https://developer.android.com/training/testing/local-tests)
* [UI tests](https://developer.android.com/jetpack/compose/testing) using fake data with
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-testing)


## Usage
1. Clone the project
2. Cd in the project
3. Run `./tools/setup.sh` or `bash tools/setup.sh`
4. Build and run the project
