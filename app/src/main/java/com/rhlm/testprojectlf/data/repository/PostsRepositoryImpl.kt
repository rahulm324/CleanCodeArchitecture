package com.rhlm.testprojectlf.data.repository

import android.util.Log
import com.rhlm.testprojectlf.core.common.Resource
import com.rhlm.testprojectlf.data.api.PostsApi
import com.rhlm.testprojectlf.data.dto.PostDtoItem
import com.rhlm.testprojectlf.domain.repository.PostRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class PostsRepositoryImpl @Inject constructor(private val postsApi: PostsApi) : PostRepository {
    override fun getPosts(): Flow<Resource<List<PostDtoItem>>> = flow{
        emit(Resource.Loading())
        //val result = generateListToDisplay()
        Log.i("TAG", "getPosts: 1")
        val result = postsApi.getPosts().execute().body()
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch { e ->
            Log.i("TAG", "getPosts: 3 ${e.message}")
            emit(Resource.Error(e.message.toString()))
        }
}