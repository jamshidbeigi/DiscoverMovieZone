package com.example.discovermovie.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MoviesResponse (
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int,
) : Parcelable
