package com.example.magicbook_projekakhirpember

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magicbook_projekakhirpember.adapter.PostAdapterRoom
import com.example.magicbook_projekakhirpember.room.PostDatabase
import com.example.magicbook_projekakhirpember.room.PostViewModel
import com.example.magicbook_projekakhirpember.room.PostViewModelFactory
import com.google.android.material.imageview.ShapeableImageView

class Bookmark : AppCompatActivity() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapterRoom: PostAdapterRoom
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        val factory = PostViewModelFactory.getInstance(this)
        postViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]
        recyclerView = findViewById(R.id.rv_post)
        recyclerView.layoutManager = LinearLayoutManager(this)

        postViewModel.getAllPost().observe(this) { postData ->
            if (postData != null) {
                postAdapterRoom = PostAdapterRoom(postData, postViewModel) //ini
                recyclerView.adapter = postAdapterRoom

                postAdapterRoom.setOnItemClickCallback(object :
                    PostAdapterRoom.OnItemClickCallback {
                    override fun onMoreClicked(data: PostDatabase, position: Int) {
                        PopUpFragment(data, position).show(supportFragmentManager, PopUpFragment.TAG)
                    }
                })
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        postViewModel.getAllPost()
    }

    fun home(view: View) {
        val intent = Intent(this@Bookmark, MainActivity::class.java)
        startActivity(intent)
    }

    fun bookmark(view: View) {
        // Do nothing because it's already on the bookmark page
    }

    fun profile(view: View) {
        val intent = Intent(this@Bookmark, Profil::class.java)
        startActivity(intent)
    }

    fun add(view: View) {
        val intent = Intent(this@Bookmark, AddPostActivity::class.java)
        startActivity(intent)
    }

    fun toAddPost(view: View) {
        val intent = Intent(this, AddPostActivity::class.java)
        startActivity(intent)
    }
}
