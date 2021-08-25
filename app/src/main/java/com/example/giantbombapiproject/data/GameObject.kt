package com.example.giantbombapiproject.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameObject(
    val name: String,
    val deck: String?,
    val image: ImageUrl,
) : Parcelable {

    @Parcelize
    data class ImageUrl(
        val screen_url: String?,
        val medium_url: String?
    ) : Parcelable {

    }
}