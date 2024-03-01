plugins {
    alias(libs.plugins.starwars.android.feature)
    alias(libs.plugins.starwars.android.library.compose)
}

android {
    namespace = "com.swapi.starwars.feature.main"

}

dependencies {

    implementation(projects.core.designsystem)
}