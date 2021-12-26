package info.example.interviewtask.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import info.example.interviewtask.models.login.LoginResponse
import info.example.interviewtask.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository = LoginRepository) : ViewModel() {

    fun makeLoginRequest(countryCode: String, phoneNumber: String) {
        repository.makeLoginRequest(countryCode, phoneNumber)
    }

    fun getLoginResponse(): LiveData<LoginResponse> = repository.getLoginResponse()

}