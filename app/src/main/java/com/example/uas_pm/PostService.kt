package com.example.uas_pm

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface PostService {
    @GET("posts")
    fun getPost():Single<ResponsePost>

    @POST("posts")
    fun addPost(@Body bodyPost: BodyPost):Single<ResponseAddPost>

    @PUT("posts/{id}")
    fun putPost(@Body bodyPost: BodyPut,@Path("id") id:Int):Single<ResponseAddPost>

    @PATCH("posts/{id}")
    fun patchPost(@Body body:HashMap<String,String>, @Path("id") id:Int):Single<ResponseAddPost>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int):Single<ResponseAddPost>
}