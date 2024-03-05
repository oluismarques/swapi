package swapi.tmdb

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension

enum class MoviesBuildType(val displayName: String, val applicationIdSuffix: String? = null) {
    Debug("debug", ".dev"),
    Release("release"),
}

fun configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *>,
    buildTypeConfigurationBlock: BuildType.(buildType: MoviesBuildType) -> Unit = {},
) {
    commonExtension.apply {
        buildFeatures.buildConfig = true
        buildTypes {
            MoviesBuildType.values().forEach {
                getByName(it.displayName) {
                    buildTypeConfigurationBlock(this, it)
                    if (this@apply is ApplicationExtension && this is ApplicationBuildType) {
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}

fun configureBuildType(
    commonExtension: CommonExtension<*, *, *, *, *>,
    buildType: MoviesBuildType,
    buildTypeConfigurationBlock: BuildType.() -> Unit = {},
) {
    configureBuildTypes(commonExtension) {
        if (it == buildType) buildTypeConfigurationBlock()
    }
}
