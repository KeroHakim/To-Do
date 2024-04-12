package com.route.to_do.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.route.to_do.databinding.SplashBinding

class SplashActivity() : AppCompatActivity() {
    private lateinit var viewBinding: SplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = SplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        Handler(mainLooper).postDelayed({
            startMainActivity()
        }, 2000)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
