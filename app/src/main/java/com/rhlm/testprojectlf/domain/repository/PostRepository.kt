package com.rhlm.testprojectlf.domain.repository

import com.rhlm.testprojectlf.core.common.Resource
import com.rhlm.testprojectlf.data.dto.PostDtoItem
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<Resource<List<PostDtoItem>>>
}