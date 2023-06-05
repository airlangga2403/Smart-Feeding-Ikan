package org.apps.smartfeeding.di

import android.content.Context
import org.apps.smartfeeding.repository.SmartFeedingRepository

object Injection {
    fun provideRepository(context: Context): SmartFeedingRepository {
        return SmartFeedingRepository.getInstance()
    }
}