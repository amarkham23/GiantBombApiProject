package com.example.giantbombapiproject.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.giantbombapiproject.api.GiantBombApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GiantBombRepository @Inject constructor(private val giantBombApi: GiantBombApi) {

    fun getSearchResults(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { GiantBombPagingSource(giantBombApi, query) }
    ).liveData
}