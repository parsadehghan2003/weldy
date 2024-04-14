plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("io.realm.kotlin")

}

android {
    namespace = "com.ftpd.cat.framework"
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(Google.material)
    testImplementation(JUnit.jUnit)
    androidTestImplementation(AndroidXTest.jUnitInstrumentTest)
    androidTestImplementation(AndroidXTest.espresso)
    implementation(Hilt.android)
    kapt(Hilt.compiler)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.12.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("io.realm.kotlin:library-base:1.8.0")

    implementation(project(":base"))
    implementation(project(":cat:domain"))
    implementation(project(":cat:data-source"))
    implementation(project(":gateway"))
    implementation(project(":database"))
}