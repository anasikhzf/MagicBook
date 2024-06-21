package com.example.magicbook_projekakhirpember

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class DetailHiburanActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hiburan)
        // Mengambil data nama, deskripsi, dan gambar dari intent
        val getDataTitle = intent.getStringExtra("title")
        val getDataPenulis = intent.getStringExtra("penulis")
        val getDataDescription = intent.getStringExtra("description")
        val getDataImage = intent.getIntExtra("image", 0)

        // Menghubungkan variabel dengan komponen di layout
        val hiburanTitle = findViewById<MaterialTextView>(R.id.hiburan_title)
        val hiburanPenulis = findViewById<MaterialTextView>(R.id.hiburan_penulis)
        val hiburanDescription = findViewById<MaterialTextView>(R.id.hiburan_description)
        val hiburanImage = findViewById<ShapeableImageView>(R.id.hiburan_image)

        // Menampilkan data pemain
        hiburanTitle.text = getDataTitle
        hiburanPenulis.text = getDataPenulis
        hiburanDescription.text = getDataDescription
        hiburanImage.setImageResource(getDataImage)
    }
    fun home(view: View) {
        val intent = Intent(this@DetailHiburanActivity, MainActivity::class.java)
        startActivity(intent)
    }
    fun bookmark(view: View) {
        val intent = Intent(this@DetailHiburanActivity, Bookmark::class.java)
        startActivity(intent)
    }
    fun profile(view: View) {
        val intent = Intent(this@DetailHiburanActivity, Profil::class.java)
        startActivity(intent)
    }
    fun add(view: View) {
        val intent = Intent(this@DetailHiburanActivity, AddPostActivity::class.java)
        startActivity(intent)
    }
}