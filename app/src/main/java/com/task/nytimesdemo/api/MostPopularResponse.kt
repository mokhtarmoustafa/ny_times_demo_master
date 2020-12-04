package com.task.nytimesdemo.api

import android.os.Parcelable
import com.task.nytimesdemo.data.Article
import kotlinx.android.parcel.Parcelize


data class MostPopularResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Article>,
    val status: String
)