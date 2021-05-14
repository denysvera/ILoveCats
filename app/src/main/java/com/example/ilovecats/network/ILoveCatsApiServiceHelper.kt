package com.example.ilovecats.network

import com.example.ilovecats.models.Cat
import kotlinx.coroutines.Deferred

interface ILoveCatsApiServiceHelper  {

    fun getCatImages(limit: Int, page: Int, order: String):Deferred<List<Cat>>
}