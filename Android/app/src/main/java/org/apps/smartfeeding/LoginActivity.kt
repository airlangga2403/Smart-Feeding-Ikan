package org.apps.smartfeeding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

            loginButton.setOnClickListener {
                startActivity(Intent(this@LoginActivity, OnlineActivity::class.java))
            }

            back.setOnClickListener {
                startActivity(Intent(this@LoginActivity, OnBoardingActivity::class.java))
            }
        }
    }
}