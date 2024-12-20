import com.android.build.gradle.internal.utils.isKotlinKaptPluginApplied

plugins {
    alias(libs.plugins.android.application)
    kotlin("android") version "2.0.21"
    id("com.google.devtools.ksp") version "2.0.21-1.0.27"
}

android {
    namespace = "com.example.project1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.project1"
        minSdk = 24
        targetSdk = 34
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
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation ("androidx.room:room-runtime:2.6.1")
    ksp(libs.androidx.room.compiler)
    implementation("com.google.android.material:material:1.10.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.support.annotations)
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // Используем ViewModel и viewModelScope
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1") // Для LiveData
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}