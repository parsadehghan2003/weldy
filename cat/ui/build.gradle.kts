plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ftpd.cat.ui"
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
    implementation(AndroidX.fragment)
    implementation(AndroidX.lifecycle_runtime_ktx)
    implementation(AndroidX.lifecycle_viewmodel)
    implementation(AndroidX.lifecycle_extenteions)
    kapt (AndroidX.lifecycle_extenteions_compiler)
    implementation(Glide.glide)
    implementation(Glide.glideCompiler)
    implementation(Glide.glideOkhttp3)
    implementation(Google.material)
    testImplementation(JUnit.jUnit)
    androidTestImplementation(AndroidXTest.jUnitInstrumentTest)
    androidTestImplementation(AndroidXTest.espresso)
    implementation(Hilt.android)
    kapt(Hilt.compiler)
    implementation(project(":base"))
    implementation(project(":cat:domain"))
    implementation(project(":cat:framework"))
    implementation(project(":cat:usecase"))
    implementation(project(":cat:data-source"))
    implementation(project(":gateway"))

}