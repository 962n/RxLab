plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    kotlin("kapt")
}
android {
    compileSdkVersion(Version.Android.compileSdkVersion)
    defaultConfig {
        applicationId = "com.example.a962n.rxlab"
        minSdkVersion(Version.Android.minSdkVersion)
        targetSdkVersion(Version.Android.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
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
    dataBinding {
        isEnabled = true
    }
    tasks {
        withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class) {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
}

dependencies {

    implementation(Deps.RxJava2.rxKotlin)
    implementation(Deps.RxJava2.rxAndroid)

    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(Deps.AndroidX.viewPager2)
    implementation(Deps.AndroidX.recyclerView)
    implementation(Deps.AndroidX.swipeRefreshLayout)

    implementation(Deps.Google.material)

    implementation(Deps.AndroidX.fragmentKtx)

//    kapt(Deps.AndroidX.lifecycleCompiler)
    implementation(Deps.AndroidX.lifecycleExt)
    implementation(Deps.AndroidX.lifecycleViewModelKtx)
    implementation(Deps.AndroidX.lifecycleLiveDataKtx)
    implementation(Deps.AndroidX.lifecycleReactiveStreams)

    implementation(Deps.AndroidX.roomRuntime)

    kapt(Deps.AndroidX.roomCompiler)
    implementation(Deps.AndroidX.roomKtx)
    implementation(Deps.AndroidX.roomRxjava2)

//    // optional - Guava support for Room, including Optional and ListenableFuture
//    implementation "androidx.room:room-guava:$room_version"

    testImplementation(Deps.AndroidX.roomTesting)

    testImplementation(Deps.junit)
    testImplementation(Deps.AndroidX.coreTesting)
    testImplementation(Deps.robolectric)

    androidTestImplementation(Deps.AndroidX.junit)
    androidTestImplementation(Deps.AndroidX.espressoCore)
}
