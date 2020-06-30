package com.githubbranching.koindependencyinjection.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubbranching.koindependencyinjection.R
import com.githubbranching.koindependencyinjection.networkadapter.model.Posts
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val postsList: MutableList<Posts> = mutableListOf()
    private var onPostClickListener: OnPostClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val customView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
        return PostViewHolder(customView)
    }

    override fun getItemCount() = postsList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.bind(postsList[position])

    fun addData(post: Posts) {
        postsList.add(post)
        notifyItemInserted(this.itemCount)
    }

    fun setListener(onPostClickListener: OnPostClickListener) {
        this.onPostClickListener = onPostClickListener
    }

    interface OnPostClickListener {
        fun onPostClicked(id: Int)
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Posts) {
            itemView.tvPostItemTitle.text = post.title
            itemView.cvPostItem.setOnClickListener { onPostClickListener?.onPostClicked(post.id) }
        }
    }
}