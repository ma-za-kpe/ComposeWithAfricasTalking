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

## Installation
Please follow these steps to set up ComposeWithAfricasTalking on your local machine.
1. Create a new, empty folder called atcompose/ within your home folder. Navigate to it (cd atcompose), then clone the ComposeWithAfricasTalking repo. This will create a new folder named atcompose/ComposeWithAfricasTalking.

- Note: Please keep the folder name as atcompose.
- Changing the project folder name might lead to future issues with running the pre-push checks on your machine.

2. Run the setup script, which adds some development tools for ComposeWithAfricasTalking (ktlint, checkstyle, etc.):
-  For Windows
    - Install Git Bash Command Line
    - Open Git Bash Command Line.
    - Navigate to atcompose/ComposeWithAfricasTalking.
    - Run the script `bash tools/setup.sh`.
    - Download the google_checks.xml(https://github.com/checkstyle/checkstyle/blob/14005e371803bd52dff429904b354dc3e72638c0/src/main/resources/google_checks.xml) file. To do this, you can simply right-click on the Raw button and click on Save Link as.
    - Copy this file to the directory where Git is installed (usually C:/Program Files/Git/).

3. Follow instruction on this https://stackoverflow.com/a/66133030/8277525 to change IntelliJ's import order for Kotlin to satisfy ktlint

4. In Android Studio, select File > Open, navigate to atcompose/ComposeWithAfricasTalking, and click OK to load the project.

5. Click the elephant icon in the toolbar ("Sync Gradle") to ensure that all the correct dependencies are downloaded. (In general, you'll want to do this step any time you update your dependencies.)

