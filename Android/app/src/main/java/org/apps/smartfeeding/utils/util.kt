package org.apps.smartfeeding.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun dateFormat(dateTimeString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val outputFormat = SimpleDateFormat("HH:mm:ss EEE, dd/MM/yyyy", Locale.US)

    return try {
        val date = inputFormat.parse(dateTimeString)
        outputFormat.format(date)
    } catch (e: Exception) {
        "Invalid Date"
    }
}