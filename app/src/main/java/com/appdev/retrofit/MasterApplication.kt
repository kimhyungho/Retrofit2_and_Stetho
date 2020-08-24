package com.appdev.retrofit

import android.app.Application
import android.content.Context
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MasterApplication : Application() {

    lateinit var service: RetrofitService

    override fun onCreate() {
        super.onCreate()
        createRetrofit()
    }

    fun createRetrofit() {
        // if you want to add headers, refer to this text
//        val header = Interceptor {
//            val original = it.request()                     // 원래 신호
//            if (checkIsLogin()) {
//                getUserToken()?.let { token ->           // null 이 아닌경우에 헤더에 token 을 넣겠다
//                    val request = original.newBuilder()             // 빌더로 원래 신호 변경
//                        .header("Authorization", "token " + token)    // 헤더 작성
//                        .build()                                    // 만들기, 실행
//                    it.proceed(request)
//                }
//            } else {
//                it.proceed(original)
//            }
//        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(RetrofitService::class.java)
    }

//    fun checkIsLogin(): Boolean {
//        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
//        val token = sp.getString("login_sp", "null")
//        if (token != "null") return true
//        else return false
//    }
//
//    fun getUserToken(): String? {
//        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
//        val token = sp.getString("login_sp", "null")
//        if (token == "null") return null
//        else return token
//    }
}
