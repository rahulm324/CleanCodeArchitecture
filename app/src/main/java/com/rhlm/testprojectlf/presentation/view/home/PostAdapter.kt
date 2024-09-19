package com.rhlm.testprojectlf.presentation.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rhlm.testprojectlf.data.dto.PostDtoItem
import com.rhlm.testprojectlf.databinding.ProductItemBinding

class PostAdapter(private val posts: MutableList<PostDtoItem>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private val TAG = "PostAdapter"
    interface OnItemTap {
        fun onTap(post: PostDtoItem)
    }

    fun setItemTapListener(l: OnItemTap){
        onTapListener = l
    }

    fun updateAdapter(mPosts : List<PostDtoItem>){
        posts.run {
            clear()
            addAll(mPosts)
            notifyDataSetChanged()
        }
    }

    private var onTapListener: OnItemTap? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.PostViewHolder {
        val view = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.i(TAG, "onCreateViewHolder:")
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.PostViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder:")
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(post: PostDtoItem){
            binding.tvTitle.text = post.title
            binding.tvContent.text = post.content
            binding.root.setOnClickListener {
                onTapListener?.onTap(post)
            }
            Log.i(TAG, "bind: PostViewHolder")
        }
    }
}