import com.swap.util.Constants.BASE_WIDTH_342_PATH
import com.swap.util.Constants.BASE_WIDTH_780_PATH
import com.swapi.tmdb.domain.detail.Cast
import com.swapi.tmdb.domain.detail.Credits
import com.swapi.tmdb.domain.detail.Crew
import com.swapi.tmdb.domain.detail.DetailItem
import com.swapi.tmdb.domain.detail.SpokenLanguage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
internal data class DetailResponse(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("belongs_to_collection")
    val belongsToCollectionResponse: BelongsToCollectionResponse?,
    @SerialName("budget")
    val budget: Int,
    @SerialName("genres")
    val genres: List<GenreResponse>,
    @SerialName("homepage")
    val homepage: String,
    @SerialName("id")
    val id: Int,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryResponse>,
    @SerialName("credits")
    val credits: CreditsResponse,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("revenue")
    val revenue: Int,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>,
    @SerialName("status")
    val status: String,
    @SerialName("tagline")
    val tagline: String,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int,
)

@Serializable
internal data class BelongsToCollectionResponse(
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("poster_path")
    val posterPath: String,
)

@Serializable
internal data class CastResponse(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("cast_id")
    val castId: Int,
    @SerialName("character")
    val character: String,
    @SerialName("credit_id")
    val creditId: String,
    @SerialName("gender")
    val gender: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("known_for_department")
    val knownForDepartment: String,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String?,
)

@Serializable
internal data class CrewResponse(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("credit_id")
    val creditId: String,
    @SerialName("department")
    val department: String,
    @SerialName("gender")
    val gender: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("job")
    val job: String,
    @SerialName("known_for_department")
    val knownForDepartment: String,
    @SerialName("name")
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String?,
)

@Serializable
internal data class CreditsResponse(
    @SerialName("cast")
    val cast: List<CastResponse>,
    @SerialName("crew")
    val crew: List<CrewResponse>,
)

@Serializable
internal data class GenreResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
)

@Serializable
internal data class ProductionCompanyResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("logo_path")
    val logoPath: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("origin_country")
    val originCountry: String?,
)

@Serializable
internal data class ProductionCountryResponse(
    @SerialName("iso_3166_1")
    val iso31661: String,
    @SerialName("name")
    val name: String,
)

@Serializable
internal data class SpokenLanguageResponse(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    @SerialName("name")
    val name: String,
)

internal fun DetailResponse.asDomainModel(): DetailItem =
    DetailItem(
        id = id,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath?.let { posterPath ->
            String.format(
                BASE_WIDTH_342_PATH,
                posterPath
            )
        },
        backdropPath = backdropPath?.let { backdropPath ->
            String.format(
                BASE_WIDTH_780_PATH,
                backdropPath
            )
        },
        runtime = runtime.toString(),
        originalTitle = originalTitle,
        voteAverage = voteAverage.roundToOneDecimal(),
        voteCount = voteCount,
        genres = genres.asGenreDomainModel(),
        homepage = homepage,
        originalLanguage = originalLanguage,
        popularity = popularity,
        spokenLanguages = spokenLanguages.asLanguageDomainModel(),
        status = status,
        tagline = tagline,
        title = title,
        credits = credits.asCreditsDomainModel()
    )

private fun List<SpokenLanguageResponse>.asLanguageDomainModel(): List<SpokenLanguage> = map {
    SpokenLanguage(it.iso6391, it.name)
}

private fun List<GenreResponse>.asGenreDomainModel(): List<String> = map {
    it.name
}

private fun CreditsResponse.asCreditsDomainModel(): Credits =
    Credits(cast = cast.asCastDomainModel(), crew = crew.asCrewDomainModel())


private fun List<CrewResponse>.asCrewDomainModel() = map {
    Crew(
        id = it.id,
        job = it.job,
        name = it.name,
        profilePath = it.profilePath?.let { profilePath ->
            String.format(
                BASE_WIDTH_780_PATH,
                profilePath
            )
        }
    )
}

private fun List<CastResponse>.asCastDomainModel() = map {
    Cast(
        id = it.id,
        character = it.character,
        name = it.name,
        profilePath = it.profilePath?.let { profilePath ->
            String.format(
                BASE_WIDTH_780_PATH,
                profilePath
            )
        }
    )
}

internal fun Double.roundToOneDecimal() =
    (this * ONE_DECIMAL_ARGUMENT).roundToInt() / ONE_DECIMAL_ARGUMENT

private const val ONE_DECIMAL_ARGUMENT = 10.0