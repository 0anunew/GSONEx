package com.example.gsonex.model

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
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


    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<Post>
    //userID=20&title=New%20Title&body=New%20Text

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @FieldMap fields: Map<String, String>
    ): Call<Post>


    //Replace all fields
    @PUT("posts/{id}")
    fun putPost(@Path("id") id: Int, @Body post: Post): Call<Post>

    //only the fields sent in the body will be updated
    @PATCH("posts/{id}")
    fun patchPost(@Path("id") id: Int, @Body post: Post): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Unit>
}

