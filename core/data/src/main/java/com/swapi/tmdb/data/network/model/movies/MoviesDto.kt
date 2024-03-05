
import com.swapi.tmdb.domain.MovieItem
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
@Serializable
internal data class MoviesResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val movieItemResponses: List<MovieItemResponse>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

@Serializable
internal data class MovieItemResponse(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("id")
    val id: Int,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

internal fun List<MovieItemResponse>.asMovieDomainModel(): List<MovieItem> =
    map {
        MovieItem(
            id = it.id,
            overview = it.overview,
            releaseDate = it.releaseDate,
            posterUrl = it.posterPath?.let { posterPath ->
                String.format(
                    BASE_WIDTH_342_PATH,
                    posterPath
                )
            },
            backdropUrl = it.backdropPath?.let { backdropPath ->
                String.format(
                    BASE_WIDTH_780_PATH,
                    backdropPath
                )
            },
            originalTitle = it.originalTitle,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount
        )
    }

const val BASE_WIDTH_342_PATH = "http://image.tmdb.org/t/p/w342%s"
const val BASE_WIDTH_780_PATH = "http://image.tmdb.org/t/p/w780%s"