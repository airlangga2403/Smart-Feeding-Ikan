package org.apps.smartfeeding.ui.connect_bluetooth

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.apps.smartfeeding.OnlineActivity
import org.apps.smartfeeding.R
import org.apps.smartfeeding.databinding.ActivityConnectBtactivityBinding
import org.apps.smartfeeding.ui.ViewModelFactory

class ConnectBTActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnectBtactivityBinding

    private val factory: ViewModelFactory by lazy {
        ViewModelFactory.getInstance(this.application)
    }
    private val viewModel: ConnectBTViewModel by viewModels {
        factory
    }
    private lateinit var deviceListAdapter: ArrayAdapter<String>
    private lateinit var deviceName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectBtactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel.initBluetooth(this).observe(this, { test ->
//            deviceListAdapter = ArrayAdapter(this, R.layout.item_text_view_device, listOf(test))
//            binding.listViewDevices.adapter = deviceListAdapter
//        })


        viewModel.initBluetooth(this)
        viewModel.deviceList.observe(this, { devices ->
            deviceListAdapter = ArrayAdapter(this, R.layout.item_text_view_device, devices)
            binding.listViewDevices.adapter = deviceListAdapter
        })


        binding.listViewDevices.setOnItemClickListener { _, _, position, _ ->
            viewModel.deviceList.observe(this, { devices ->
                if (position < devices.size) {
                    val deviceInfo = devices[position]
                    deviceName = deviceInfo.substringBefore("\n") // Extract device name
                    // Call the connectToDevice() method with the deviceName parameter
                    viewModel.connectToDevice(deviceName, this@ConnectBTActivity)
                }
            })
        }


        binding.nextButton.setOnClickListener {
            viewModel.isConnected.observe(this, { status ->
                if (status) {
                    val intent = Intent(this, OnlineActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Select Device First ", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }


}