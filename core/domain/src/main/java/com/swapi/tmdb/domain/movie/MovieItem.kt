package com.swapi.tmdb.domain.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
     val id: Int,
     val overview: String,
     val releaseDate: String?,
     val posterUrl: String?,
     val backdropUrl: String?,
     val originalTitle: String,
     val voteAverage: Double,
     val voteCount: Int,
) : Parcelable