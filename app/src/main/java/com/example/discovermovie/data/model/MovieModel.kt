package com.example.discovermovie.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
class MovieModel(
    val adult:Boolean,
    val backdrop_path:String,
    val genre_ids:List<Int>,
    val id:Int,
    val original_language:String,
    val original_title:String,
    val overview:String,
    val popularity:Double,
    val poster_path:String,
    val release_date:String,
    val title:String,
    val video:Boolean,
    val vote_average:Double,
    val vote_count:Int
) : Parcelable

//
//{
//    "results": [
//    {
//        "adult": false,
//        "backdrop_path": "/yyFc8Iclt2jxPmLztbP617xXllT.jpg",
//        "genre_ids": [
//        35,
//        10751,
//        14
//        ],
//        "id": 787699,
//        "original_language": "en",
//        "original_title": "Wonka",
//        "overview": "Willy Wonka – chock-full of ideas and determined to change the world one delectable bite at a time – is proof that the best things in life begin with a dream, and if you’re lucky enough to meet Willy Wonka, anything is possible.",
//        "popularity": 2244.452,
//        "poster_path": "/qhb1qOilapbapxWQn9jtRCMwXJF.jpg",
//        "release_date": "2023-12-06",
//        "title": "Wonka",
//        "video": false,
//        "vote_average": 7.237,
//        "vote_count": 1776
//    },
//}