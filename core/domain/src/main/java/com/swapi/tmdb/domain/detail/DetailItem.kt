package com.swapi.tmdb.domain.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailItem(
    val backdropPath: String?,
    val genres: List<String>,
    val homepage: String?,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String?,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val runtime: String,
    val credits: Credits,
) : Parcelable

@Parcelize
data class SpokenLanguage(
    val iso6391: String,
    val name: String,
) : Parcelable

@Parcelize
data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
) : Parcelable

@Parcelize
data class Cast(
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?,
) : Parcelable

@Parcelize
data class Crew(
    val id: Int,
    val name: String,
    val job: String,
    val profilePath: String?,
) : Parcelable
