package com.example.gsonex.model

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface JSONPlaceholderAPI {


    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") id: Int): Call<MutableList<Comment>>

    @GET
    fun getComments(@Url url: String): Call<MutableList<Comment>>

    @GET("posts")
    fun getPostsByUserId(@Query("userId") userId: Int): Call<MutableList<Post>>

    //?userId=6&_sort=id&_order=desc
    @GET("posts")
    fun getPostsByUserIdAndSort(
        @Query("_sort") sort: String,
        @Query("_order") order: String,
        @Query("userId") vararg userId: Int
    ): Call<MutableList<Post>>

    @GET("posts")
    fun getPostsByUserIdAndSort(
        @QueryMap parameters:Map<String,String>
    ):Call<MutableList<Post>>

}