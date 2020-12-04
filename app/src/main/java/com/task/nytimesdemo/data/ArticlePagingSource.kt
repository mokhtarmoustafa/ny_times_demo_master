package com.task.nytimesdemo.data


import androidx.paging.PagingSource
import com.task.nytimesdemo.api.NYTimesApi
import retrofit2.HttpException
import java.io.IOException

private const val NY_TIMES_STARTING_PAGE_INDEX = 1

class ArticlePagingSource(
    private val nyTimesApi: NYTimesApi,
    private val section: String,
    private val period: Int,
    private val apiKey: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: NY_TIMES_STARTING_PAGE_INDEX

        return try {
            val response = nyTimesApi.getMostPopularArticles(section, period , apiKey)
            val articles = response.results

            LoadResult.Page(
                data = articles,
                prevKey = if (position == NY_TIMES_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (articles.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}