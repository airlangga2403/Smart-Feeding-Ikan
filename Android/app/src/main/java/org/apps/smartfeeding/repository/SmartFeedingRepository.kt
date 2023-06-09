package org.apps.smartfeeding.repository

import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.util.UUID

class SmartFeedingRepository() {

    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var deviceNamesSet: HashSet<String>


    var lastButtonClickTime: Long = 0
    val debounceDelay = 1000 // Debounce delay in milliseconds

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected: LiveData<Boolean> = _isConnected

    private val _deviceList = MutableLiveData<List<String>>()
    val deviceList: LiveData<List<String>> = _deviceList

    private val _bluetoothSocket = MutableLiveData<BluetoothSocket?>()
    val bluetoothSocket: MutableLiveData<BluetoothSocket?> = _bluetoothSocket

    private val _receivedData = MutableLiveData<String>()
    val receivedData: LiveData<String> = _receivedData

    fun initializeBluetooth(context: Context) {
        _isConnected.value = false
        val bluetoothManager: BluetoothManager =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.getSystemService(BluetoothManager::class.java) as BluetoothManager
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        bluetoothAdapter = bluetoothManager.adapter

        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            // Handle this condition accordingly
            Toast.makeText(context, "Sorry Your Device Not Support Bluetooth", Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (!bluetoothAdapter.isEnabled) {
            Toast.makeText(context, "Please Enable Bluetooth First", Toast.LENGTH_SHORT).show()
        }

        deviceNamesSet = HashSet()

        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter.bondedDevices
        val devicesList = pairedDevices?.map { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address
            "$deviceName\n$deviceHardwareAddress" // Combine name and address
        }
        _deviceList.postValue(devicesList!!)
    }

    fun connectToDevice(deviceName: String, context: Context) {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter.bondedDevices
        val device = pairedDevices?.firstOrNull { it.name == deviceName }
        if (device != null) {
            ConnectToDeviceTask(context).execute(device)
        }
    }


    fun readDataFromBluetooth() {
        val buffer = ByteArray(1024)
        val inputStream: InputStream = bluetoothSocket.value?.inputStream ?: return

        try {
            val bytes: Int = inputStream.read(buffer)
            val receivedData = buffer.decodeToString(0, bytes)

            // Lakukan sesuatu dengan data yang diterima
//                    handleReceivedData(receivedData)
            _receivedData.value = receivedData
        } catch (e: IOException) {
            e.printStackTrace()
            // Tangani kesalahan pembacaan data
        }
    }


    fun sendDataToBluetooth(data: String, context: Context) {
        val socket = bluetoothSocket.value
        if (socket != null) {
            try {
                socket.outputStream.write(data.toByteArray())
                // Handle successful data sending
                Toast.makeText(context, "Sukses Sending Data ${data}", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle failed data sending
            }
        } else {
            // Handle not connected to any device
        }
    }

    fun disconnect() {
        val socket = bluetoothSocket.value
        if (socket != null) {
            try {
                _isConnected.value = false
                socket.close()
                _bluetoothSocket.value = null
                // Handle disconnection
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private inner class ConnectToDeviceTask(private val context: Context) :
        AsyncTask<BluetoothDevice, Void, Boolean>() {

        private var progressDialog: ProgressDialog? = null

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(context)
            progressDialog?.setMessage("Connecting...")
            progressDialog?.setCancelable(false)
            progressDialog?.show()
        }

        // AsyncTask to connect to a Bluetooth device
        override fun doInBackground(vararg devices: BluetoothDevice): Boolean {
            val device = devices[0]
            try {
                val socket: BluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID)
                socket.connect()
                _bluetoothSocket.postValue(socket)
                return true
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return false
        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)
            progressDialog?.dismiss()
            progressDialog = null
            if (result) {
                _isConnected.value = true
                Toast.makeText(context, "Sukses Connect Bluetooth", Toast.LENGTH_SHORT).show()
                // Handle successful connection
            } else {
                // Handle failed connection
                Toast.makeText(context, "Failed Connect Bluetooth", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        private const val TAG = "SmartFeedingRepository"

        @Volatile
        private var instance: SmartFeedingRepository? = null
        fun getInstance(

        ): SmartFeedingRepository =
            instance ?: synchronized(this) {
                instance ?: SmartFeedingRepository()
            }.also { instance = it }
    }
}