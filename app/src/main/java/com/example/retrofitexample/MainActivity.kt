package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitexample.databinding.ActivityMainBinding
import com.example.retrofitexample.retrofit.ProductAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)


        //First we need to init Retrofit instance:
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
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