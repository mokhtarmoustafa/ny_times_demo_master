package com.task.nytimesdemo

import com.task.nytimesdemo.util.Constants

interface TestConstants {
    companion object {
        //variables
        const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/"
        val API_KEY: String = BuildConfig.NY_TIMES_API_KEY
        val CURRENT_SECTION= Constants.CURRENT_SECTION
        val DEFAULT_PERIOD_WEEKLY=Constants.DEFAULT_PERIOD_WEEKLY
    }
}