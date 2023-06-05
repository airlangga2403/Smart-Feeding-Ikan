package org.apps.smartfeeding.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.apps.smartfeeding.di.Injection
import org.apps.smartfeeding.repository.SmartFeedingRepository
import org.apps.smartfeeding.ui.connect_bluetooth.ConnectBTViewModel
import org.apps.smartfeeding.ui.dashboard.StatusPakanViewModel
import org.apps.smartfeeding.ui.home.HomeViewModel
import org.apps.smartfeeding.ui.notifications.OtomatisViewModel

class ViewModelFactory private constructor(private val smartFeedingRepository: SmartFeedingRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConnectBTViewModel::class.java)) {
            return ConnectBTViewModel(smartFeedingRepository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(smartFeedingRepository) as T
        } else if (modelClass.isAssignableFrom(StatusPakanViewModel::class.java)) {
            return StatusPakanViewModel(smartFeedingRepository) as T
        } else if (modelClass.isAssignableFrom(OtomatisViewModel::class.java)) {
            return OtomatisViewModel(smartFeedingRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}