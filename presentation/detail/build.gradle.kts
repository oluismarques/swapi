plugins {
    alias(libs.plugins.tmdb.android.feature)
    alias(libs.plugins.tmdb.android.library.compose)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.swapi.tmdb.feature.detail"

}

dependencies {

    implementation(projects.core.designsystem)
    implementation(projects.core.util)

    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.kotlinx.datetime)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}