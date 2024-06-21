package com.example.magicbook_projekakhirpember

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.magicbook_projekakhirpember.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var adapter: ImageSliderAdapter
    private val list = ArrayList<viewpagerdata>()
    private lateinit var dots: ArrayList<TextView>

    private lateinit var usernameEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        digunakan untuk set viewpager
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(viewpagerdata(R.drawable.slide1))
        list.add(viewpagerdata(R.drawable.slide2))
        list.add(viewpagerdata(R.drawable.slide3))
        list.add(viewpagerdata(R.drawable.slide4))

        adapter = ImageSliderAdapter(list)
        binding.viewPager2.adapter = adapter

        dots = ArrayList()
        setIndicator()

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })


//        digunakan untuk setting login

        usernameEditText = findViewById(R.id.username_edit)
        passwordEditText = findViewById(R.id.password_edit)

        val registerTextView: TextView = findViewById(R.id.register)
        registerTextView.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            loginUser()
        }

    }

    private fun loginUser() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val registeredUsername = sharedPreferences.getString("username", null)
        val registeredPassword = sharedPreferences.getString("password", null)

        if (username == registeredUsername && password == registeredPassword) {
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Login, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until list.size) {
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_secondary))
        }
    }

    private fun setIndicator() {
        for (i in 0 until list.size) {
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#9679;", Html.FROM_HTML_MODE_LEGACY)
            dots[i].textSize = 18f
            dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_secondary))
            binding.dotsIndicator.addView(dots[i])
        }
        dots[0].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary))
    }
}
