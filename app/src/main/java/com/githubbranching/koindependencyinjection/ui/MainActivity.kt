package com.githubbranching.koindependencyinjection.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.githubbranching.koindependencyinjection.R
import com.githubbranching.koindependencyinjection.ui.adapter.PostAdapter
import com.githubbranching.koindependencyinjection.ui.base.PostState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val adapter: PostAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val student1 = get<Student>()
        student1.beSmart()

        val student2 = get<Student>()
        student2.beSmart()*/

        rvPostList.adapter = adapter

        viewModel.getPostsState().observe(this, Observer {
            when (it) {
                is PostState.AddPost -> adapter.addData(it.post)
                is PostState.Error ->
                    Toast.makeText(
                        this, it.message ?: getString(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
            }
            if (progressBar.visibility == View.VISIBLE) progressBar.visibility = View.GONE
        })

        viewModel.getPosts()
    }
}