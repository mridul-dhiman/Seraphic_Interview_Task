package info.example.interviewtask.models.login

data class LoginRequest(
    val language: String,
    val countryCode: String,
    val phoneNo: String,
    val deviceType: String,
    val deviceToken: String
)
