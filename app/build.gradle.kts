
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id("com.google.devtools.ksp") // Habilita o plugin KSP
}
android {
    namespace = "com.example.ListFilmes"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.ListFilmes"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        //dataBinding = false
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}
dependencies {
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.navigation:navigation-ui-ktx:2.8.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation("androidx.compose.material3:material3:1.3.1") // Substitua pela versão mais recente
    //implementation("androidx.compose.ui:ui:1.7.5")
    implementation("androidx.compose.runtime:runtime:1.7.5")

    implementation(libs.androidx.room.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.androidx.material3.android)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

