package com.task.nytimesdemo

import android.content.Context
import android.net.Uri
import androidx.annotation.RawRes
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.task.nytimesdemo.api.MostPopularResponse
import com.task.nytimesdemo.api.NYTimesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.util.stream.Collectors


class TestUtils {
    private fun getJson(fileName: String): String {
        try {
            val inputStream = openFile(fileName)
            val result = BufferedReader(InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"))
            return result.toString()


        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""


    }

    @Throws(IOException::class)
    private fun openFile(filename: String): InputStream? {
        return javaClass.classLoader.getResourceAsStream(filename)
    }



    fun getArticles(): MostPopularResponse {
        return provideJson().fromJson(
            getJson("articles_data.json"),
            MostPopularResponse::class.java
        )
    }



    private fun provideJson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideJson()))
            .client(okHttpClient())
            .baseUrl(NYTimesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}
