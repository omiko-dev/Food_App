# Food Mobile App -> "TastyCraft"

Foodie is a mobile app developed in Kotlin that allows users to explore various food recipes. Users can view recipes, add them to favorites, watch cooking videos on YouTube, and access detailed information about each recipe.

## Table of Contents
- [Features](#features)
- [Dependencies](#dependencies)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features
- Browse a collection of delicious food recipes
- Add recipes to favorites for quick access
- Watch cooking tutorial videos on YouTube
- View detailed information about each recipe
- Explore the source of each recipe

## Dependencies
- **Navigation:** 
  - implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
  - implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

- **Retrofit:** 
  - implementation("com.squareup.retrofit2:retrofit:2.9.0")
  - implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

- **OkHttp3:** 
  - implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

- **Moshi:** 
  - implementation("com.squareup.moshi:moshi-kotlin:1.15.0")

- **Dagger Hilt:** 
  - implementation("com.google.dagger:hilt-android:2.50")
  - kapt("com.google.dagger:hilt-compiler:2.50")

- **Glide:** 
  - implementation("com.github.bumptech.glide:glide:4.16.0")

- **Room Database:** 
  - implementation("androidx.room:room-runtime:2.6.1")
  - annotationProcessor("androidx.room:room-compiler:2.6.1")
  - implementation("androidx.room:room-ktx:2.6.1")
  - ksp("androidx.room:room-compiler:2.6.1")

- **Firebase:** 
  - implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
  - implementation("com.google.firebase:firebase-analytics")
  - implementation("com.google.firebase:firebase-core:21.1.1")
  - implementation("com.google.firebase:firebase-auth:22.3.1")
  - implementation("com.google.firebase:firebase-firestore:24.10.1")

## Installation
1. Clone the repository: git clone https://github.com/omiko-dev/Food_App.git
2. Open the project in Android Studio
3. Build and run the app on an emulator or physical device

## Usage
1. Launch the app on your device
2. Browse through the collection of food recipes
3. Add your favorite recipes to the favorites list
4. Watch cooking tutorial videos on YouTube
5. View detailed information about each recipe
6. Explore the source of each recipe for more details


