package com.example.magicbook_projekakhirpember

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Profil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
    }
    fun home(view: View) {
        val intent = Intent(this@Profil, MainActivity::class.java)
        startActivity(intent)
    }
    fun bookmark(view: View) {
        val intent = Intent(this@Profil, Bookmark::class.java)
        startActivity(intent)
    }
    fun profile(view: View) {
        val intent = Intent(this@Profil, Profil::class.java)
        startActivity(intent)
    }
    fun add(view: View) {
        val intent = Intent(this@Profil, AddPostActivity::class.java)
        startActivity(intent)
    }
}