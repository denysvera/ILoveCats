package com.example.ilovecats.repository

import com.example.ilovecats.network.ILoveCatsApiServiceHelper
import javax.inject.Inject

class ILoveCatsRepository @Inject constructor(private val iLoveCatsApiServiceHelper: ILoveCatsApiServiceHelper) {

    suspend fun getCats(limit: Int, page: Int, order: String) = iLoveCatsApiServiceHelper.getCatImages(limit, page, order)
}