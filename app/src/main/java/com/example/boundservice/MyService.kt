package com.example.boundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    fun bindServiceTest(): String {
        return "abcdefg"
    }

    inner class MyServiceBinder : Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("create", "create")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.e("bind","binding")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("unbind","unbinding")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("destory","Destory")
    }


    val binder = MyServiceBinder()
}