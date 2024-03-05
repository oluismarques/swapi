plugins {
    alias(libs.plugins.tmdb.android.library)
}

android {
    namespace = "com.swap.tmdb.util"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.coroutines.android)
}