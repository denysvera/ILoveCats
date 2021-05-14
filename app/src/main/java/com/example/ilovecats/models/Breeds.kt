package com.example.ilovecats.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breeds(val id: String,
                  val name: String,
                  val temperament: String,
                  @SerializedName("life_span")val lifeSpan: String,
                  @SerializedName("alt_names")val altName: String,
                  @SerializedName("wikipedia_url")val wikipediaUrl: String ): Parcelable
