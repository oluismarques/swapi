plugins {
    alias(libs.plugins.starwars.android.feature)
    alias(libs.plugins.starwars.android.library.compose)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.swapi.starwars.launchpad"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

}

dependencies {
    implementation(projects.core.designsystem)

    implementation(libs.androidx.compose.material3.windowSizeClass)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}