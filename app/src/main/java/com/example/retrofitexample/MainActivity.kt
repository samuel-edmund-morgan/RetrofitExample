package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitexample.databinding.ActivityMainBinding
import com.example.retrofitexample.retrofit.RetrofitInstance
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)


        //Let's run our GET request in coroutine by pressing button (we SHOULD NOT use
        // network requests in main thread
        activityMainBinding.button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val retrofitInstance = RetrofitInstance.retrofit.getProductById(1)
                //Change coroutine to main thread then
                withContext(Dispatchers.Main){
                    activityMainBinding.textView.text = retrofitInstance.title
                }
            }
        }
    }
}