package com.task.nytimesdemo.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NetworkReceiver @Inject constructor(var networkUtil: NetworkUtil) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
//        // TODO: This method is called when the BroadcastReceiver is receiving
//        // an Intent broadcast. throw new UnsupportedOperationException("Not yet implemented");
        val status = networkUtil.connectivityStatusString
        if (status != null) {
            Toast.makeText(context, status, Toast.LENGTH_LONG).show()
        }
    }
}