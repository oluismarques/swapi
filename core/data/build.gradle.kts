plugins {
    alias(libs.plugins.starwars.android.library)
    alias(libs.plugins.starwars.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.swapi.starwars.core.data"

    buildFeatures.buildConfig = true

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BaseUrl",
                "\"https://api.spacexdata.com/v3/\""
            )
        }
        release {
            buildConfigField(
                "String",
                "BaseUrl",
                "\"https://api.spacexdata.com/v3/\""
            )
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

}