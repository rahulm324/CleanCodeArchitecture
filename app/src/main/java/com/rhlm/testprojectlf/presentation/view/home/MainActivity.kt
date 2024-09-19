package com.rhlm.testprojectlf.presentation.view.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rhlm.testprojectlf.R
import com.rhlm.testprojectlf.data.dto.PostDtoItem
import com.rhlm.testprojectlf.databinding.ActivityMainBinding
import com.rhlm.testprojectlf.presentation.viewmodels.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"

    private val postsViewModel: PostsViewModel by viewModels()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(_binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _binding?.rvPosts?.layoutManager = LinearLayoutManager(applicationContext,
            RecyclerView.VERTICAL,
            false)
        _binding?.rvPosts?.setHasFixedSize(true)

        setUpRecyclerview()
        observePostState()
        postsViewModel.getAllPosts()
    }

    private fun observePostState() {
        lifecycleScope.launch {
            postsViewModel.postsState.collect { postsState ->
                if (postsState.isLoading) {
                    Log.i(tag, "onCreate: post state loading state")
                }
                if (postsState.error.isNotEmpty()) {
                    Log.i(tag, "onCreate: post state error ")
                }

                if (!postsState.posts.isNullOrEmpty()){
                    Log.i(tag, "onCreate: post state success1 ${binding?.rvPosts?.adapter} ")

                    binding?.rvPosts?.adapter?.let {
                        Log.i(tag, "onCreate: post state success2 ")
                        if (it is PostAdapter) {
                            Log.i(tag, "onCreate: post state success3 ")
                            it.updateAdapter(postsState.posts)
                            //it.notifyDataSetChanged()
                        }
                    }
                    postsViewModel.postsState.value.posts?.forEach {
                        Log.i(tag, "onCreate: ${it.id} ${it.title} ${it.content} ")
                    }
                }
            }
        }
    }

    private fun setUpRecyclerview() {
        if(_binding == null){
            Log.i(tag, "setUpRecyclerview binding is null")
        }


        val mAdapter = PostAdapter(mutableListOf())
        _binding?.rvPosts?.adapter = mAdapter

        mAdapter.setItemTapListener(object : PostAdapter.OnItemTap {
            override fun onTap(post: PostDtoItem) {
                val b = bundleOf("post_id" to post.id)
                Toast.makeText(this@MainActivity, post.title, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}