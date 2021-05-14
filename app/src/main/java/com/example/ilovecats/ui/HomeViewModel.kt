package com.example.ilovecats.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ilovecats.Helpers.ApiStatus
import com.example.ilovecats.models.Cat
import com.example.ilovecats.network.CatsImagePagingSource
import com.example.ilovecats.repository.ILoveCatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val iLoveCatsRepository: ILoveCatsRepository) : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    var errorMessage: String? =""
    private var _catImageList = MutableLiveData<List<Cat>>()
    val catImageList: LiveData<List<Cat>> get() = _catImageList

    init {
        getCatImages()
    }
    fun getCatImages(): Flow<PagingData<Cat>>{

        return Pager(config = PagingConfig(pageSize = 20,enablePlaceholders = false, prefetchDistance = 2),
            pagingSourceFactory = {CatsImagePagingSource(iLoveCatsRepository)}).flow.cachedIn(viewModelScope)


    }
}