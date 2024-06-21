package com.example.magicbook_projekakhirpember


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.magicbook_projekakhirpember.room.PostDatabase
import com.example.magicbook_projekakhirpember.room.PostViewModel
import com.example.magicbook_projekakhirpember.room.PostViewModelFactory
import com.example.magicbook_projekakhirpember.utils.reduceFileImage
import com.example.magicbook_projekakhirpember.utils.uriToFile
import com.google.android.material.textfield.TextInputEditText

class AddPostActivity : AppCompatActivity() {

    private var currentImageUri: Uri? = null
    private lateinit var postViewModel: PostViewModel
    private lateinit var vPostTitle: TextInputEditText
    private lateinit var vPostPenulis: TextInputEditText
    private lateinit var vPostDesc: TextInputEditText
    private lateinit var vPostImage: ImageView
    private lateinit var vtextImg: TextView

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            vPostImage.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            vtextImg.text = "change"
            Glide.with(vPostImage)
                .load(firstImage.uri)
                .into(vPostImage)
        } else {
            vPostImage.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        val factory = PostViewModelFactory.getInstance(this)
        postViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]

        vPostTitle = findViewById(R.id.title_edit)
        vPostPenulis = findViewById(R.id.title_edit_text)
        vPostDesc = findViewById(R.id.description_edit_text)
        vPostImage = findViewById(R.id.cover_image)
        vtextImg = findViewById(R.id.text_img)

        onClick()
    }

    private fun onClick() {
        val openImagePicker = findViewById<ImageView>(R.id.cover_image)
        openImagePicker.setOnClickListener {
            imagePickerLauncher.launch(
                ImagePickerConfig {
                    mode = ImagePickerMode.SINGLE
                    returnMode = ReturnMode.ALL
                    isFolderMode = true
                    folderTitle = "Galeri"
                    isShowCamera = false
                    imageTitle = "Click to choose the image"
                    doneButtonText = "Done"
                }
            )
        }

        val btnSavedPost = findViewById<Button>(R.id.btn_savedPost)
        btnSavedPost.setOnClickListener {
            if (validateInput()) {
                saveData()
            }
        }
    }

    private fun validateInput(): Boolean {
        var error = 0

        if (vPostTitle.text.toString().isEmpty()) {
            error++
            vPostTitle.error = "Title is not empty!"
        }
        if (vPostPenulis.text.toString().isEmpty()) {
            error++
            vPostPenulis.error = "Author is not empty!"
        }
        if (vPostDesc.text.toString().isEmpty()) {
            error++
            vPostDesc.error = "Desc is not empty!"
        }
        if (vtextImg.text.toString() == "add") {
            error++
            vtextImg.error = "Image is not empty!"
        }

        return error == 0
    }

    private fun saveData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val post = imageFile?.let {
            PostDatabase(
                id = 0,
                name = vPostTitle.text.toString(),
                penulis = vPostPenulis.text.toString(),
                description = vPostDesc.text.toString(),
                image = it
            )
        }

        if (post != null) {
            postViewModel.insertPost(post)
            Toast.makeText(
                this@AddPostActivity,
                "Data successfully added",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        } else {
            Toast.makeText(
                this@AddPostActivity,
                "Failed to add data",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun toMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun home(view: View) {
        val intent = Intent(this@AddPostActivity, MainActivity::class.java)
        startActivity(intent)
    }

    fun bookmark(view: View) {
        val intent = Intent(this@AddPostActivity, Bookmark::class.java)
        startActivity(intent)
    }

    fun profile(view: View) {
        val intent = Intent(this@AddPostActivity, Profil::class.java)
        startActivity(intent)
    }

    fun add(view: View) {
        val intent = Intent(this@AddPostActivity, AddPostActivity::class.java)
        startActivity(intent)
    }

}
