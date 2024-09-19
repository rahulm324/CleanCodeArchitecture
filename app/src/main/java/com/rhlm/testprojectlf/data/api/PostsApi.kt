package com.rhlm.testprojectlf.data.api

import com.rhlm.testprojectlf.data.dto.PostDtoItem
import retrofit2.Call
import retrofit2.http.GET


interface PostsApi {
    @GET("blogposts")
    fun getPosts() : Call<List<PostDtoItem>>
}