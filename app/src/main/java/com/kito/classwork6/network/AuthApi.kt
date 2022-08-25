package com.lukabaia.classwork_6.network

import com.kito.classwork6.models.LogIn
import com.kito.classwork6.models.Register
import com.kito.classwork6.models.Request
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun getLoginForm(@Body userInfo: Request): Response<LogIn>

    @POST("register")
    suspend fun getRegisterForm(@Body userInfo: Request): Response<Register>

}