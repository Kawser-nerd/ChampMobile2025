plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.googlemapintegration"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.googlemapintegration"
        minSdk = 34
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //implementation("com.google.android.libraries.maps:maps:3.1.0-beta")
    //implementation("com.google.maps.android:maps-v3-ktx:3.4.0")
    //implementation("com.google.maps.android:maps-v3-ktx:2.2.0")
    //implementation("com.google.maps.android:maps-compose:2.11.4")
    //implementation("androidx.fragment:fragment:1.8.6")
    //implementation("com.google.android.gms:play-services-maps:19.1.0")
    //implementation()
    //implementation("com.android.volley:volley:1.2.1")
    // Google Maps SDK for Android
    //implementation("com.google.android.libraries.places:places:4.2.0")
    implementation(libs.places)
    //implementation("com.google.android.gms:play-services-maps:19.1.0")
    implementation(libs.play.services.maps)

// Google maps Compose
    //implementation("com.google.maps.android:maps-compose:4.3.3")
    implementation(libs.maps.compose)

}