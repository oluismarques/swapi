import com.android.build.api.dsl.ApplicationBuildType
import swapi.starwars.StarWarsBuildType
import swapi.starwars.configureBuildType


plugins {
    alias(libs.plugins.starwars.android.application)
    alias(libs.plugins.starwars.android.application.compose)
    alias(libs.plugins.starwars.android.hilt)
}

android {
    namespace = "com.swapi.starwars"

    defaultConfig {
        applicationId = "com.swapi.starwars"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    configureBuildType(this, buildType = StarWarsBuildType.Release) {
        this as ApplicationBuildType

        isMinifyEnabled = true
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }

}

dependencies {
    implementation(projects.presentation.launchpad)

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
