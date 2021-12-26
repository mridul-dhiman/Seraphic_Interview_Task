package info.example.interviewtask.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPClient {
    val getClient: APIClient by lazy {
        Retrofit.Builder().baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIClient::class.java)
    }
}