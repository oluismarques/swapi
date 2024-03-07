package com.swapi.tmdb.domain.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
     val id: Int,
     val releaseDate: String?,
     val posterUrl: String?,
     val originalTitle: String,
     val voteAverage: Double,
) : Parcelable