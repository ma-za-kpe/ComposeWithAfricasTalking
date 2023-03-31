# ComposeWithAfricasTalking
- A simple project demonstrating the use of some of the Africa's talking API's in a 
jetpack compose way. 
- We also heavily utilise the android documentation, code labs, youtube videos, open source projects like nowinandroid, and so on. 
- This project is under heavy construction, and will remain as so, for the foreseen future. This project is built with scalability and expansion in mind.

| #Light Mode    | #2 Dark Mode   |
| :---: | :---: |
| ![Screenshot_20230326_173918](https://user-images.githubusercontent.com/28810111/227786216-c562c549-3b6f-468c-89df-40f1481fcd6b.png)   | ![Screenshot_20230326_174015](https://user-images.githubusercontent.com/28810111/227786209-ba4c105c-a907-4346-865e-d783982279fe.png)   |

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
    - Run the script bash scripts/setup.sh.
    - Download the google_checks.xml(https://github.com/checkstyle/checkstyle/blob/14005e371803bd52dff429904b354dc3e72638c0/src/main/resources/google_checks.xml) file. To do this, you can simply right-click on the Raw button and click on Save Link as.
    - Copy this file to the directory where Git is installed (usually C:/Program Files/Git/).

3. Follow instruction on this https://stackoverflow.com/a/66133030/8277525 to change IntelliJ's import order for Kotlin to satisfy ktlint

4. In Android Studio, select File > Open, navigate to atcompose/ComposeWithAfricasTalking, and click OK to load the project.

5. Click the elephant icon in the toolbar ("Sync Gradle") to ensure that all the correct dependencies are downloaded. (In general, you'll want to do this step any time you update your dependencies.)

