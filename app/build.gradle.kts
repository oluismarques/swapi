import com.android.build.api.dsl.ApplicationBuildType
import swapi.tmdb.MoviesBuildType
import swapi.tmdb.configureBuildType


plugins {
    alias(libs.plugins.tmdb.android.application)
    alias(libs.plugins.tmdb.android.application.compose)
    alias(libs.plugins.tmdb.android.hilt)
}

android {
    namespace = "com.swapi.tmdb"

    defaultConfig {
        applicationId = "com.swapi.tmdb"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    configureBuildType(this, buildType = MoviesBuildType.Release) {
        this as ApplicationBuildType

        isMinifyEnabled = true
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
        )
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.presentation.launchpad)
    implementation(projects.presentation.detail)
    implementation(projects.presentation.search)

    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.material3.windowSizeClass)
}
