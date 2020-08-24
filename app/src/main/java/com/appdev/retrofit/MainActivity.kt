package com.appdev.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = "username"
        val password = "password"
        (application as MasterApplication).service.login(
            username, password
        ).enqueue(object : Callback<Class> {
            override fun onFailure(call: Call<Class>, t: Throwable) {

            }

            override fun onResponse(call: Call<Class>, response: Response<Class>) {
                if (response.isSuccessful) {

                }
            }
        })
    }
}