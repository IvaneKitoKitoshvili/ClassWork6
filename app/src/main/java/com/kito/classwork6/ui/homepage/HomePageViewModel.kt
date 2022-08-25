package com.kito.classwork6.ui.homepage

import androidx.lifecycle.ViewModel
import com.kito.classwork6.data.DataStoreHandler

class HomePageViewModel: ViewModel () {

    fun getPreferences() = DataStoreHandler.getPreferences()

    suspend fun clear() {
        DataStoreHandler.clear()
    }

    // With Remove
    suspend fun remove(key: String) {
        DataStoreHandler.remove(key)
    }

}