package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.retrofitexample.databinding.ActivityMainBinding
import com.example.retrofitexample.retrofit_auth_example.AuthData
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(activityMainBinding.root)

        //Let's run our GET request in coroutine by pressing button (we SHOULD NOT use
        // network requests in main thread
        activityMainBinding.signIn.setOnClickListener {
            //In this example I make textView invisible
            activityMainBinding.imageView.visibility = View.INVISIBLE
            activityMainBinding.tvName.visibility = View.INVISIBLE
            activityMainBinding.tvSurname.visibility = View.INVISIBLE
            activityMainBinding.progressBar.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {
                //Here in real app we need to create try-catch and catch IOException and HttpException
                val response = RetrofitInstance.retrofit_auth.getUser(AuthData(
                    activityMainBinding.username.text.toString(),
                    activityMainBinding.password.text.toString()
                ))
                //Change coroutine to main thread then
                withContext(Dispatchers.Main){
                    if(response.isSuccessful) {
                        activityMainBinding.apply {
                            Picasso.get().load(response.body()!!.image).into(imageView)
                            tvName.text = response.body()!!.firstName
                            tvSurname.text = response.body()!!.lastName

                            progressBar.visibility = View.GONE

                            imageView.visibility = View.VISIBLE
                            tvName.visibility = View.VISIBLE
                            tvSurname.visibility = View.VISIBLE

                        }
                    }

                }
            }
        }
    }
}