plugins {
    alias(libs.plugins.starwars.android.library)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.swapi.starwars.core.domain"

}

dependencies {

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)

    ksp(libs.hilt.compiler)
}