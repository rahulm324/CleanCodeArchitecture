package com.rhlm.testprojectlf.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.rhlm.testprojectlf.R
import com.rhlm.testprojectlf.presentation.viewmodels.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val postsViewModel: PostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        postsViewModel.getAllPosts()
        lifecycleScope.launch {
            postsViewModel.postsState.collect { postsState ->
                if (postsState.isLoading) {
                    Log.i(tag, "onCreate: post state loading state")
                } else if (postsState.error.isNotEmpty()) {
                    Log.i(tag, "onCreate: post state error ")
                } else {
                    Log.i(tag, "onCreate: post state success ")
                    postsViewModel.postsState.value.posts?.forEach {
                        Log.i(tag, "onCreate: ${it.id} ${it.title} ${it.content} ")
                    }
                }
            }
        }

        Log.i(tag, "onCreate: ")
    }
}