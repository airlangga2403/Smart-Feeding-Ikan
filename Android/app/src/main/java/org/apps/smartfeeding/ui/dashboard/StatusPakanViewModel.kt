package org.apps.smartfeeding.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.apps.smartfeeding.repository.SmartFeedingRepository

class StatusPakanViewModel(private val smartFeedingRepository: SmartFeedingRepository) : ViewModel() {

    fun sendDataToBluetooth(data: String, context: Context) {
        smartFeedingRepository.sendDataToBluetooth(data, context)
    }

    fun readDataFromBluetooth() {
        smartFeedingRepository.readDataFromBluetooth()
    }

    val isConnected: LiveData<Boolean>
        get() = smartFeedingRepository.isConnected

    val readDataFromBT: LiveData<String>
        get() = smartFeedingRepository.receivedData
}