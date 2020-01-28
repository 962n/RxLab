object Deps {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.Kotlin.kotlin}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val coreKtx = "androidx.core:core-ktx:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.0"
        const val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Version.AndroidX.lifecycle}"
        const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.AndroidX.lifecycle}"
        const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.AndroidX.lifecycle}"
        const val lifecycleReactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams:${Version.AndroidX.lifecycle}"
        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val junit = "androidx.test.ext:junit:1.1.0"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
    }

    object RxJava2 {
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    }

    const val junit = "junit:junit:4.12"
    const val robolectric = "org.robolectric:robolectric:4.3.1"

}