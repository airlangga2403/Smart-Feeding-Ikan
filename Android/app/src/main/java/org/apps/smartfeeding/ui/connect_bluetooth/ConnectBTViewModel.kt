package org.apps.smartfeeding.ui.connect_bluetooth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.apps.smartfeeding.repository.SmartFeedingRepository

class ConnectBTViewModel(private val smartFeedingRepository: SmartFeedingRepository) : ViewModel() {
    val deviceList: LiveData<List<String>>
        get() = smartFeedingRepository.deviceList

    fun initBluetooth(context: Context) {
        smartFeedingRepository.initializeBluetooth(context)
    }

    fun connectToDevice(deviceName: String, context: Context) {
        smartFeedingRepository.connectToDevice(deviceName, context)
    }

    fun disconnect() {
        smartFeedingRepository.disconnect()
    }

    val isConnected: LiveData<Boolean>
        get() = smartFeedingRepository.isConnected
}