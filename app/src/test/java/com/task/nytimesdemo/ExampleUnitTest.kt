package com.task.nytimesdemo

import com.google.gson.Gson
import com.task.nytimesdemo.api.MostPopularResponse
import com.task.nytimesdemo.api.NYTimesApi
import io.reactivex.functions.Predicate
import io.reactivex.observers.TestObserver
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    var nyTimesApi: NYTimesApi? = null
    var retrofit: Retrofit? = null
    var testUtils: TestUtils? = null




    @Before
    fun before() {
        testUtils = TestUtils()
        retrofit = testUtils!!.buildRetrofit()
        nyTimesApi = retrofit!!.create(NYTimesApi::class.java)
    }


    @Test
     fun testResponse(): Unit = runBlocking {

    val data=  nyTimesApi?.getMostPopularArticles(
            TestConstants.CURRENT_SECTION,
            TestConstants.DEFAULT_PERIOD_WEEKLY,
            TestConstants.API_KEY
        )
        delay(1000)

        val response: MostPopularResponse = testUtils!!.getArticles()
        val testedResponse: MostPopularResponse? = data
        val json = Gson().toJson(data)
        testedResponse?.copyright.equals(response.copyright)

    }

}
