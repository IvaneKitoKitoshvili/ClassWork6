package com.kito.classwork6.ui.registerpage

import androidx.lifecycle.ViewModel
import com.kito.classwork6.models.Register
import com.kito.classwork6.models.Request
import com.kito.classwork6.network.RetrofitInstance
import com.kito.classwork6.utils.ResponseHandler
import kotlinx.coroutines.flow.flow

class RegisterPageViewModel : ViewModel() {

    fun getRegisterFlow(userInfo: Request) = flow<ResponseHandler> {
        emit(ResponseHandler.Loader(isLoading = true))
        val response = RetrofitInstance.getAuthApi().getRegisterForm(userInfo)
        if (response.isSuccessful && response.body() != null) {
            emit(ResponseHandler.Success<Register>(response.body()!!))
        } else {
            emit(ResponseHandler.Error(response.errorBody()?.string() ?: "Error!"))
        }
        emit(ResponseHandler.Loader(isLoading = false))
    }
}