package com.example.ilovecats.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ilovecats.Helpers.Helpers
import com.example.ilovecats.Helpers.Helpers.Companion.paginationPage
import com.example.ilovecats.models.Cat
import com.example.ilovecats.repository.ILoveCatsRepository

class CatsImagePagingSource(val iLoveCatsApiServiceHelper: ILoveCatsRepository) : PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
       return try {
           var nextPage: Int = params.key ?: FIRST_PAGE_INDEX
           var catImagesDeferred = iLoveCatsApiServiceHelper.getCats(20,nextPage,"DESC")
            val response = catImagesDeferred.await()
           var nextPageNumber = paginationPage!!.toInt()
           nextPage = nextPageNumber+ 1
           LoadResult.Page(response,null,nextPage)
       }catch (e: Exception){
           LoadResult.Error(e)
       }
    }
    companion object{
       private const val FIRST_PAGE_INDEX = 0
    }
}