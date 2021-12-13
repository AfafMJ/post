package com.afaf.post

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APIInterface {

    @GET("test/")
    fun getUser(): Call<data>

    @POST("test/")
    fun addUser(@Body data: dataItem): Call<dataItem>
}