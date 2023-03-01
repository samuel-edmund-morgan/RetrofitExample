package com.example.retrofitexample.retrofit

import retrofit2.Response
import retrofit2.http.*

interface ProductAPI {
    //GET, POST, PUT, DELETE
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int) : Product
    //In function above we can return Response<Product> (I don't know what's the difference yet)

    //To get single static item:
    //@GET("products/1")
    //suspend fun getProduct() : Product

    // Make a GET request with query parameter (usually for auth keys)
    //@GET("products/1")
    //suspend fun getProduct(@Query("Specify the name of query parameter that API accepts (like: key)") key: String) : Product


    //If you want to post some json data to the server
    //If you want to attach JSON - you need to use @Body
    //@POST("products/1")
    //suspend fun createProduct(@Body product: Product): Response<Product>
}