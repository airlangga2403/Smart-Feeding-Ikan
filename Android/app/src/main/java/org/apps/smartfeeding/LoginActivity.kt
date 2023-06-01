package org.apps.smartfeeding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.apps.smartfeeding.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        navigate()
    }

    private fun navigate() {
        binding.apply {
            registerButton.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }

            loginButton.setOnClickListener {
                startActivity(Intent(this@LoginActivity, WifiActivity::class.java))
            }

            back.setOnClickListener {
                startActivity(Intent(this@LoginActivity, OnBoardingActivity::class.java))
            }
        }
    }
}