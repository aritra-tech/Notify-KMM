# Notify-KMM
Notify-KMM is a simple note taking application build to demonstrate the use of Kotlin Multiplatform Mobile for developing Android and iOS applications using Jetpack Compose 🚀.

### ⚠️⚠️ _WORK IN PROGRESS_ ⚠️⚠️

| Platforms | ![](https://img.shields.io/badge/Android-black.svg?style=for-the-badge&logo=android) ![](https://img.shields.io/badge/iOS-black.svg?style=for-the-badge&logo=apple)  |
|-----------|---|


## 👨‍💻 Tech stack

| Tools                 |                                     Link                                                                  |
|:----------------------|:---------------------------------------------------------------------------------------------------------:|
| 🤖  Language          |                       [Kotlin](https://kotlinlang.org)                                                    |
| 🩶  Framework         |  [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform)                              |
| ✏️   Database                |          [SQLDelight](https://github.com/cashapp/sqldelight)                                       |

## How to run the project? ✅

To run this project, you need the following:

* A machine running a recent version of macOS
* [Xcode](https://apps.apple.com/us/app/xcode/id497799835)
* [Android Studio](https://developer.android.com/studio)

The [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile)

## Check your environment

Before you start, use the [KDoctor](https://github.com/Kotlin/kdoctor) tool to ensure that your
development environment is configured correctly:

1. Install KDoctor with [Homebrew](https://brew.sh/): ``brew install kdoctor``

2. Run KDoctor in your terminal: ``kdoctor``

   If everything is set up correctly, you'll see valid output:

   ```text
   Environment diagnose (to see all details, use -v option):
   [✓] Operation System
   [✓] Java
   [✓] Android Studio
   [✓] Xcode
   [✓] Cocoapods
   
   Conclusion:
     ✓ Your system is ready for Kotlin Multiplatform Mobile development!
   ```

Otherwise, KDoctor will highlight which parts of your setup still need to be configured and will
suggest a way to fix them.

## Project structure 

This Compose Multiplatform project includes three modules:

### [`shared`](/shared)
This is a Kotlin module that contains the logic common for both Android and iOS applications, the code you share between platforms.
This shared module is also where you write your Compose Multiplatform code. In `shared/src/commonMain/kotlin/App.kt`, you can find the shared root `@Composable` function for your app.
It uses Gradle as the build system. You can add dependencies and change settings in `shared/build.gradle.kts`. The shared module builds into an Android library and an iOS framework.

### [`androidApp`](/androidApp)
This is a Kotlin module that builds into an Android application. It uses Gradle as the build system. The `androidApp` module depends on and uses the shared module as a regular Android library.

### [`iosApp`](/iosApp)
This is an Xcode project that builds into an iOS application. It depends on and uses the shared module as a CocoaPods dependency.

## License

```
   Copyright 2023 Aritra Das

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
