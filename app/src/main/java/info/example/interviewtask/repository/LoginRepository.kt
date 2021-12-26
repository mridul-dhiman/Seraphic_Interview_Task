package info.example.interviewtask.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.example.interviewtask.models.login.LoginRequest
import info.example.interviewtask.models.login.LoginResponse
import info.example.interviewtask.utils.APIClient
import info.example.interviewtask.utils.HTTPClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

object LoginRepository : ViewModel() {
    private const val TAG = "LoginRepository"

    private val APIClient: APIClient = HTTPClient.getClient

    private val loginResponse: MutableLiveData<LoginResponse> =
        MutableLiveData<LoginResponse>()

    fun makeLoginRequest(countryCode: String, phoneNumber: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<LoginResponse> =
                    APIClient.loginUser(
                        LoginRequest(
                            "en",
                            countryCode,
                            phoneNumber,
                            "1",
                            "6464646666"
                        )
                    )

                Log.d(TAG, "makeLoginRequest: Response $response")
                Log.d(TAG, "makeLoginRequest: Response success ${response.isSuccessful}")
                Log.d(TAG, "makeLoginRequest: Response body ${response.body()}")

                loginResponse.postValue(response.body())

            } catch (exception: Exception) {
                Log.d(TAG, "makeLoginRequest: exception ${exception.localizedMessage}")

                loginResponse.postValue(LoginResponse(404,exception.localizedMessage,null,null))
            }
        }

    }

    fun getLoginResponse(): LiveData<LoginResponse> = loginResponse

}