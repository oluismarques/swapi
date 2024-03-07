import com.swap.util.Constants.BASE_WIDTH_342_PATH
import com.swap.util.Constants.BASE_WIDTH_780_PATH
import com.swapi.tmdb.domain.movie.MovieItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MoviesResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val movieItemResponses: List<MovieItemResponse>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int,
)

@Serializable
internal data class MovieItemResponse(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
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
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int,
)

internal fun List<MovieItemResponse>.asDomainModel(): List<MovieItem> =
    map {
        it.asDomainModel()
    }

internal fun MovieItemResponse.asDomainModel(): MovieItem =
    MovieItem(
        id = id,
        overview = overview,
        releaseDate = releaseDate,
        posterUrl = posterPath?.let { posterPath ->
            String.format(
                BASE_WIDTH_342_PATH,
                posterPath
            )
        },
        backdropUrl = backdropPath?.let { backdropPath ->
            String.format(
                BASE_WIDTH_780_PATH,
                backdropPath
            )
        },
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
