package com.task.nytimesdemo.api

import com.task.nytimesdemo.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTimesApi {

    companion object {
        const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
        const val API_KEY = BuildConfig.NY_TIMES_API_KEY
    }


    @GET("mostviewed/{section}/{period}.json")
    suspend fun getMostPopularArticles(
        @Path("section") section: String,
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): MostPopularResponse
}
