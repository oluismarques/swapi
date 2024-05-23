plugins {
    alias(libs.plugins.tmdb.android.library)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.swapi.tmdb.core.domain"

}

dependencies {
    implementation(projects.core.util)

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.common)

    ksp(libs.hilt.compiler)
}