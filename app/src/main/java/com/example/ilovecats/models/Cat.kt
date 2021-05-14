package com.example.ilovecats.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(val id: String,
               val url: String,
               val breeds: List<Breeds>,
               val width: Int,
               val height: Int): Parcelable
