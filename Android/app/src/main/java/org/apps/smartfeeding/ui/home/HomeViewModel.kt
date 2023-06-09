package org.apps.smartfeeding.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.apps.smartfeeding.repository.SmartFeedingRepository

class HomeViewModel(private val smartFeedingRepository: SmartFeedingRepository) : ViewModel() {

    fun sendDataToBluetooth(data: String, context: Context) {
        smartFeedingRepository.sendDataToBluetooth(data, context)
    }
    val isConnected: LiveData<Boolean>
        get() = smartFeedingRepository.isConnected
}