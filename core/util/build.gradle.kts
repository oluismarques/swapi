plugins {
    alias(libs.plugins.tmdb.android.library)
}

android {
    namespace = "com.swap.tmdb.util"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
    api(libs.androidx.compose.ui.test)
    implementation(libs.androidx.ui.test.android)
}