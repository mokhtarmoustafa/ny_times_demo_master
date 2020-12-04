package com.task.nytimesdemo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

import com.task.nytimesdemo.api.NYTimesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NYTimesRepository @Inject constructor(private val nyTimesApi: NYTimesApi) {

    fun getMostPopularArticles(section: String,period:Int,apiKey:String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArticlePagingSource(nyTimesApi, section,period,apiKey) }
        ).liveData
}