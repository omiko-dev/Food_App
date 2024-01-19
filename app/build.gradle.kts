plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("androidx.room")
}

room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "com.example.food_recept"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.food_recept"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "FOOD_BASE_URL", "\"https://www.themealdb.com/api/json/v1/1/\"")
            buildConfigField("String", "USER_BASE_URL", "\"http://10.0.2.2:5055/api/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "FOOD_BASE_URL", "\"https://www.themealdb.com/api/json/v1/1/\"")
            buildConfigField("String", "USER_BASE_URL", "\"http://10.0.2.2:5055/api/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // okHttp3
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")

    // dagger hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    // glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // room database
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
}

kapt {
    correctErrorTypes = true
}
