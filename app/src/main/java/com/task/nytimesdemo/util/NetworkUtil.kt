package com.task.nytimesdemo.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import com.task.nytimesdemo.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtil @Inject constructor(@param:ApplicationContext var context: Context) {
    val connectivityStatusString: String?
        get() {
            var status: String? = null
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                }
            } else {
                status = context.getString(R.string.internet_connection_error)
                return status
            }
            return status
        }




}