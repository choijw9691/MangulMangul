package com.didimstory.mangul

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object Client {
    var retrofitService: API


    init {
  /*     var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY*/
        var logger = OkHttpClient.Builder() .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();

        val retrofit = Retrofit.Builder()
                .baseUrl("http://ymedu88.cafe24.com/")
                .addConverterFactory(GsonConverterFactory.create())
            .client(logger)
                .build()

        retrofitService = retrofit.create(API::class.java)

    }

}
/*
object Client1 {

    val defaultHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request().newBuilder()
                .addHeader("Content-Type", "image/jpeg")
                .build()
            return chain.proceed(request)
        }
    }).build()


}
*/


