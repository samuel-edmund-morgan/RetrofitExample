package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitexample.databinding.ActivityMainBinding
import com.example.retrofitexample.retrofit.ProductAPI
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        //If we want (we WANT for debugging) to monitor which requests were sent, let's
        //create interceptor and OkHttp client:
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()



        //First we need to init Retrofit instance (with client for debugging in Logcat):
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Next we need to init API instance:
        val productApi = retrofit.create(ProductAPI::class.java)





        //Let's run our GET request in coroutine by pressing button (we SHOULD NOT use
        // network requests in main thread
        activityMainBinding.button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
            val product = productApi.getProductById(1)
                withContext(Dispatchers.Main){
                    activityMainBinding.textView.text = product.title
                }
            }
        }


    }


}