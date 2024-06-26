plugins {
    alias(libs.plugins.tmdb.android.feature)
    alias(libs.plugins.tmdb.android.library.compose)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.swapi.tmdb.feature.search"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.util)

    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.compose.material3.windowSizeClass)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    implementation(libs.androidx.compose.ui.test)
}