# Basic Weather Composable

## A basic application built with Jetpack Compose and Weather API.

### Technologies

* Compose: For layout;
* Coroutines/Flows: To work with asynchronous functions;
* OkHttp3: For HTTP requests;
* Hilt: For data injection.

### Structure

* Clean Architecture with MVVM ;
* Modular structure;
* Interface-based.

### Weather API
* https://www.weatherapi.com/

### Running the project

> To execute this application you need to install the Android Studio and run the application on your cellphone or in the Android Emulator.

1. Install Android Studio: Follow these steps  [Install Android Studio](https://developer.android.com/studio/install);
2. Go to [Weather API website](https://www.weatherapi.com/) and create you own API key;
3. Rename the file `key.properties.example` to `key.properties`;
4. Open the `key.properties` file in *BasicWeatherComposable* root folder;
5. Set your api key.

> Example: WEATHER_API=a1b2c3d4

**ATTENTION:** This application only runs on Android M (API 24) or higher versions.

### Preview
<img src="screenshots/demo_recording.gif" width="40%"/>
