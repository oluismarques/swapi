plugins {
    alias(libs.plugins.tmdb.android.library)
    alias(libs.plugins.tmdb.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.swapi.tmdb.core.data"

    buildFeatures.buildConfig = true

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BaseUrl",
                "\"http://api.themoviedb.org/\""
            )
            buildConfigField(
                "String",
                "API_KEY",
                "\"${getProperty("local.properties", "tmdb_api_key") ?: System.getenv("API_KEY")}\""
            )
        }
        release {
            buildConfigField(
                "String",
                "BaseUrl",
                "\"http://api.themoviedb.org/\""
            )

            buildConfigField(
                "String",
                "API_KEY",
                "\"${getProperty("local.properties", "tmdb_api_key") ?: System.getenv("API_KEY")}\""
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
    implementation(projects.core.util)

    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

}

fun getProperty(filename: String, propName: String): String? {
    val propsFile = rootProject.file(filename)
    if (propsFile.exists()) {
        return com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty(propName)
    } else {
        print("$filename does not exist!")
    }
    return null
}