package com.example.retrofitexample

import com.example.retrofitexample.retrofit_auth_example.AuthAPI
import com.example.retrofitexample.retrofit_example.ProductAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    //If we want (we WANT for debugging purposes) to monitor which requests were sent, let's
    //create interceptor and OkHttp client:
    private val interceptor = HttpLoggingInterceptor().apply {
        level =  HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()



    //First we need to init Retrofit instance (with client for debugging in Logcat):
    val retrofit_product: ProductAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            //Next we need to init API instance:
            //val productApi = retrofit.create(ProductAPI::class.java)
            .create(ProductAPI::class.java)
    }

    val retrofit_auth: AuthAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            //Next we need to init API instance:
            //val productApi = retrofit.create(ProductAPI::class.java)
            .create(AuthAPI::class.java)
    }


}