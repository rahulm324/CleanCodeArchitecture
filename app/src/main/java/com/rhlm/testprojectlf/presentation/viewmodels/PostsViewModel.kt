package com.rhlm.testprojectlf.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhlm.testprojectlf.core.common.Resource
import com.rhlm.testprojectlf.domain.usecases.GetAllPostsUseCase
import com.rhlm.testprojectlf.presentation.states.PostsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PostsViewModel @Inject constructor(private val useCase: GetAllPostsUseCase) : ViewModel(){

    /* some difference between live data and stateFlow
    * StateFlow requires an initial state to be passed in to the constructor, while LiveData does not
    * LiveData.observe() automatically unregisters the consumer when the view goes to the STOPPED state,
    * whereas collecting from a StateFlow or any other flow does not stop collecting automatically.
    * */
    private val _postsState = MutableStateFlow(PostsState())           //here we have passed PostState as initial state
    val postsState : StateFlow<PostsState>
        get() = _postsState

    fun getAllPosts() {
        viewModelScope.launch {
            useCase.invoke().onEach {
                when (it) {
                    is Resource.Error -> {
                        _postsState.value = PostsState().copy(error = it.message.toString())
                    }

                    is Resource.Loading -> {
                        _postsState.value = PostsState().copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        Log.i("TAG", "getAllPosts: from view model")
                        _postsState.value = PostsState().copy(posts = it.data ?: emptyList())
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}
