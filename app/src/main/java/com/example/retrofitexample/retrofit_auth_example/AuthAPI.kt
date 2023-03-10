package com.example.retrofitexample.retrofit_auth_example

import com.example.retrofitexample.retrofit_example.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthAPI {
    @POST("auth/login")
    suspend fun getUser(@Body authData: AuthData) : Response<User>
}