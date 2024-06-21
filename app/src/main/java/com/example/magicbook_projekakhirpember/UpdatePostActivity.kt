package com.example.magicbook_projekakhirpember


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import java.io.File

class UpdatePostActivity : AppCompatActivity() {
    private var currentImageUri: Uri? = null
    private var oldPhoto: File? = null

    private lateinit var postViewModel: PostViewModel
    private lateinit var vPostTitle: TextInputEditText
    private lateinit var vPostPenulis: TextInputEditText
    private lateinit var vPostDesc: TextInputEditText
    private lateinit var vPostImage: ImageView
    private lateinit var vTextImg: TextView
    private lateinit var getData: PostDatabase

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            vPostImage.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            vTextImg.setText("change")
            Glide.with(vPostImage)
                .load(firstImage.uri)
                .into(vPostImage)
        } else {
            View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_update_post)

        getData = intent.getParcelableExtra("post")!!
        val postViewModelFactory = PostViewModelFactory.getInstance(this)
        postViewModel = ViewModelProvider(this, postViewModelFactory)[PostViewModel::class.java]

        vPostTitle = findViewById(R.id.post_title_update)
        vPostPenulis = findViewById(R.id.post_penulis_update)
        vPostDesc = findViewById(R.id.post_desc_update)
        vPostImage = findViewById(R.id.post_img_update)
        vTextImg = findViewById(R.id.text_img_update)

        vPostTitle.setText(getData.name)
        vPostPenulis.setText(getData.penulis)
        vPostDesc.setText(getData.description)
        vTextImg.setText("change")

        oldPhoto = getData.image
        Glide.with(vPostImage)
            .load(getData.image)
            .into(vPostImage)

        onClick()
    }

    private fun onClick() {
        val openImagePicker = findViewById<ImageView>(R.id.post_img_update)
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

        val btnSavePost = findViewById<Button>(R.id.btn_updatedPost)
        btnSavePost.setOnClickListener {
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
        if (vTextImg.text.toString() == "add") {
            error++
            vTextImg.error = "Image is not empty!"
        }

        return error == 0
    }

    private fun saveData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val post = (if (currentImageUri != null) imageFile else oldPhoto)?.let {
            PostDatabase(
                id = getData.id,
                name = vPostTitle.text.toString(),
                penulis = vPostPenulis.text.toString(),
                description = vPostDesc.text.toString(),
                image = it
            )
        }

        if (post != null) {
            Log.d("UpdatePostActivity", "Post Data: $post")
            postViewModel.updatePost(post)
            Toast.makeText(
                this@UpdatePostActivity,
                "Data successfully updated",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this@UpdatePostActivity,
                "Data failed to update",
                Toast.LENGTH_SHORT
            ).show()
        }

        finish()
    }

    fun toMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
