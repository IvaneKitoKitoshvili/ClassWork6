package com.kito.classwork6.ui.loginpage

import androidx.lifecycle.ViewModel
import com.kito.classwork6.RetrofitClient.getLoginParameter
import com.kito.classwork6.models.LogIn
import com.kito.classwork6.models.Request
import com.kito.classwork6.retrofitclient.ResultHendler
import kotlinx.coroutines.flow.flow

class LogInPageViewModel : ViewModel() {
    fun logIn(email: String, password: String)= flow<ResultHendler<LogIn>> {
        val answerFromServer = getLoginParameter().login(Request(email, password))
        val response: ResultHendler<LogIn> = when {
            answerFromServer.isSuccessful -> {
                ResultHendler.Success(data = answerFromServer.body()!!)
            }
            else -> {
                ResultHendler.Error(errorMSg = answerFromServer.errorBody()!!.string())
            }
        }
        emit (response)
    }
}