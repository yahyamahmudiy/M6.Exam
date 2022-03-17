package com.example.m6exam.Networking

import com.example.m6exam.Model.CardItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers(
        "Content-type:application/json"
    )

    @GET("card")
    fun listCards(): Call<ArrayList<CardItem>>

    @POST("card")
    fun createCard(@Body card: CardItem):Call<CardItem>

/*    @GET("posts/{id}")
    fun singlePost(@Path("id")id:Int):Call<PosterResp>

    @POST("posts")
    fun createPost(@Body post: Poster):Call<PosterResp>

    @PUT("posts/{id}")
    fun updatePost(@Path("id")id: Int,@Body post: PosterResp):Call<PosterResp>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id")id:Int):Call<PosterResp>  */

}