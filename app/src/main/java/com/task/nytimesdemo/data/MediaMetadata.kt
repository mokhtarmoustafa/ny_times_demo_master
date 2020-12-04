package com.task.nytimesdemo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
):Parcelable