package com.rhlm.testprojectlf.domain.usecases

import com.rhlm.testprojectlf.domain.repository.PostRepository
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(private val repository: PostRepository) {
    operator fun invoke() = repository.getPosts()
}