package org.apps.smartfeeding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.apps.smartfeeding.databinding.ActivityOnBoardingBinding
import org.apps.smartfeeding.ui.connect_bluetooth.ConnectBTActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        navigate()

    }

    private fun navigate() {
        binding.apply {

            // Online
            wifiButton.setOnClickListener {
                val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }

            // Offline

            bluetoothButton.setOnClickListener {
                val intent = Intent(this@OnBoardingActivity, ConnectBTActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}