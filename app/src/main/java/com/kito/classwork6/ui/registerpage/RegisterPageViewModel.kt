package com.kito.classwork6.ui.registerpage

import androidx.lifecycle.ViewModel
import com.kito.classwork6.RetrofitClient
import com.kito.classwork6.models.Register
import com.kito.classwork6.models.Request
import com.kito.classwork6.retrofitclient.ResultHendler
import kotlinx.coroutines.flow.flow

class RegisterPageViewModel : ViewModel() {
    fun register(email: String, password: String) = flow<ResultHendler<Register>> {
        val answerFromServer =
            RetrofitClient.getRegisterParameter().register(Request(email, password))
        val response: ResultHendler<Register> = when {
            answerFromServer.isSuccessful -> {
                ResultHendler.Success(data = answerFromServer.body()!!)
            }
            else -> {
                ResultHendler.Error(errorMSg = answerFromServer.errorBody()!!.string())
            }
        }
        emit(response)
    }
}