package com.example.gsonex.model

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.http.Query

interface JSONPlaceholderAPI{


    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") id: Int): Call<MutableList<Comment>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId: Int): Call<MutableList<Post>>

}