package com.example.boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.boundservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var myService:MyService? = null
    var isConService = false
    val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val b = service as MyService.MyServiceBinder
            myService = b.getService()
            isConService = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isConService = false
        }
    }

    fun serviceBind()
    {
        val intent = Intent(this, MyService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun serviceUnBind()
    {
        if (isConService) {
            unbindService(serviceConnection)
            isConService = false
        }
    }

    fun callBindServiceTest() {
        if (isConService) {
            val rtn = myService?.bindServiceTest()
            Log.d("test", "value=${rtn}")
        }
        else {
            Log.d("test", "no service")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

        btnStart.setOnClickListener {
            serviceBind()
        }

        btnStop.setOnClickListener {
            serviceUnBind()
        }

        btnCallMethod.setOnClickListener {
            callBindServiceTest()
            }
        }
    }


}