package com.afaf.post

import retrofit2.Call
import retrofit2.http.*


interface APIInterface {

    @GET("test/")
    fun getUser(): Call<data>

    @POST("test/")
    fun addUser(@Body data: dataItem): Call<dataItem>
    @PUT("test/{id}")
    fun updateUser(@Path("id")id: Int , @Body data: dataItem): Call<dataItem>

    @DELETE("test/{id}")
    fun deleteUser(@Path("id")id: Int ): Call<Void>
}