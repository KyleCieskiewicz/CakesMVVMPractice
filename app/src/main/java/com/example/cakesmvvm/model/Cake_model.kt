package com.example.cakesmvvm.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cake_model(
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("desc")
    @Expose
    var desc: String? = null,
    @SerializedName("image")
    @Expose
    var image: String? = null
): Parcelable{
}