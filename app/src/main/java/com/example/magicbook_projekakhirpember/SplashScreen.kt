package com.example.magicbook_projekakhirpember

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            goToLoginActivity()
        },3000L)
    }

    private fun goToLoginActivity(){
        Intent(this, Login::class.java).also{
            startActivity(it)
            finish()
        }
    }
}