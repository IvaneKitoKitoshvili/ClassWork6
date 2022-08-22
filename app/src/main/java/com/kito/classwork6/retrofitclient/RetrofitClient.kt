package com.kito.classwork6

import com.kito.classwork6.models.LogIn
import com.kito.classwork6.models.Register
import com.kito.classwork6.models.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object RetrofitClient {

    private const val BASE_URL = "https://reqres.in/api/"
    val retrofitBuilder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getLoginParameter() =
        retrofitBuilder.create(LogInService::class.java)

    fun getRegisterParameter() =
        retrofitBuilder.create(RegisterService::class.java)

}


interface LogInService {
    @POST("login")
    suspend fun login(@Body requestModel: Request): Response<LogIn>
}

interface RegisterService {
    @POST("register")
    suspend fun register(@Body requestModel: Request): Response<Register>
}
