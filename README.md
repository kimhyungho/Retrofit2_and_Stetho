Retrofit
============
How to use Retrofit

Official Homepage
-----------
https://square.github.io/retrofit/


Version
-----------
 retrofit:2.9.0
 retrofit2:converter-gson:2.6.2 
 
What is Retrofit?
-----------
Retrofit은 Square사에서 제공하는 오픈소스 라이브러리로 REST API를 안드로이드에서 쉽게 이용할 수 있게 해주는 도구 입니다.
클라이언트에서 서버로 어떠한 요청을 보내면 요청에 대한 응답을 클라이언트로 보내주는데, 이 과정을 도와주는 역할을 해줍니다.

사용법
-----------

* 앱 수준의 build.gradle
```
dependencies {
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
}
```
* MasterApplication   (class)
```
import android.app.Application
import com.facebook.stetho.Stetho
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MasterApplication : Application() {

    lateinit var service: RetrofitService

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        createRetrofit()
        // chrome://inspect/#device
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
//          .client(client)         // if you want to add a client, refer to this line
            .build()
        service = retrofit.create(RetrofitService::class.java)
    }
    //  login function example
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
```
* AndroidManifest.xml
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="">

    <uses-permission android:name="android.permission.INTERNET" />

</manifest>
```
* RetrofitService
```
// GET, POST, 
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {
    // example GET, POST, PUT, DELETE ...
    @POST("user/login/")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<Class>
}
```

* Class (YOU CAN RECIEVE THIS CLASS FORM)
```
import java.io.Serializable

class Class(
    var receiveObject: String? = null
) : Serializable
```

* MainActivity
```
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
```
