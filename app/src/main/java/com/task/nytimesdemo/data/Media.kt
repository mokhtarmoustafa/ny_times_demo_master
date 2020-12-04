package com.task.nytimesdemo.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val media_metadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
) : Parcelable