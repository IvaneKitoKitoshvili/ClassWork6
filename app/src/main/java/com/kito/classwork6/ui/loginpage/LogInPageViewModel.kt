package com.kito.classwork6.ui.loginpage

import androidx.lifecycle.ViewModel
import com.kito.classwork6.data.DataStoreHandler
import com.kito.classwork6.models.LogIn
import com.kito.classwork6.models.Request
import com.kito.classwork6.network.RetrofitInstance
import com.kito.classwork6.utils.ResponseHandler
import kotlinx.coroutines.flow.flow

class LogInPageViewModel : ViewModel() {

    fun getLoginFlow(Request: Request) = flow<ResponseHandler> {
        emit(ResponseHandler.Loader(isLoading = true))
        val response = RetrofitInstance.getAuthApi().getLoginForm(Request)
        if (response.isSuccessful && response.body() != null) {
            emit(ResponseHandler.Success<LogIn>(response.body()!!))
        } else {
            emit(ResponseHandler.Error(response.errorBody()?.string() ?: "Error!"))
        }
        emit(ResponseHandler.Loader(isLoading = false))
    }

    suspend fun save(key: String, value: String) {
        DataStoreHandler.save(key, value)
    }

    fun getPreferences() = DataStoreHandler.getPreferences()

}