// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:3.5.3")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Kotlin.kotlin}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

tasks.create("clean",type = Delete::class) {
    delete(rootProject.buildDir)
}
