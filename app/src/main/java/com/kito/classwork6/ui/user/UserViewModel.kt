package com.kito.classwork6.ui.user

import android.util.Log.d
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    override fun onCleared() {
        d("KITOTEST", "USERVIEWMODELONDESTROY")
        super.onCleared()
    }
}