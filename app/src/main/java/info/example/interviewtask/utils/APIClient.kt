package info.example.interviewtask.utils

import info.example.interviewtask.models.login.LoginRequest
import info.example.interviewtask.models.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIClient {

    @POST("auth/signin")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}