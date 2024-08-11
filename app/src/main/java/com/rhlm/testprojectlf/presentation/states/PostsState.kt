package com.rhlm.testprojectlf.presentation.states

import com.rhlm.testprojectlf.data.dto.PostDtoItem

data class PostsState(
    val posts : List<PostDtoItem>? = emptyList(),
    val error : String = "",
    val isLoading : Boolean = false
)
