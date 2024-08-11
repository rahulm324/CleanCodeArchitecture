package com.rhlm.testprojectlf.data.api

import com.rhlm.testprojectlf.data.dto.PostDtoItem
import retrofit.Call
import retrofit.http.GET

interface PostsApi {
    @GET("blogposts")
    fun getPosts() : Call<List<PostDtoItem>>
}