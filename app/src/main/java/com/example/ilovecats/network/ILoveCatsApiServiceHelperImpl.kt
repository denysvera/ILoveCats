package com.example.ilovecats.network

import com.example.ilovecats.models.Cat
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ILoveCatsApiServiceHelperImpl @Inject constructor(private val iLoveCatsApiService: ILoveCatsApiService): ILoveCatsApiServiceHelper {
    override  fun getCatImages(limit: Int, page: Int, order: String): Deferred<List<Cat>> = iLoveCatsApiService.getCatImages(limit, page, order)
}