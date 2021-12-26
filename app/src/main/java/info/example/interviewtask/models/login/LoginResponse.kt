package info.example.interviewtask.models.login

data class LoginResponse(
    val statusCode: Int,
    val message: String?,
    val data: LoginResponseData?,
    val error: String?
)
