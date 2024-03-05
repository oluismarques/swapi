plugins {
    alias(libs.plugins.tmdb.android.feature)
    alias(libs.plugins.tmdb.android.library.compose)
}

android {
    namespace = "com.swapi.tmdb.feature.main"

}

dependencies {

    implementation(projects.core.designsystem)
}