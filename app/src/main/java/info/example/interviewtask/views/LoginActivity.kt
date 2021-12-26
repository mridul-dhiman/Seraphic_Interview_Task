package info.example.interviewtask.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import info.example.interviewtask.R
import info.example.interviewtask.databinding.ActivityLoginBinding
import info.example.interviewtask.viewModels.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityLLLogin.setOnClickListener {

            binding.apply {
                val currentText: String = mainActivityOTFPhone.editText?.text.toString()

                if (currentText.length == 10) {
                    if (mainActivityOTFPhone.isErrorEnabled) {
                        mainActivityOTFPhone.isErrorEnabled = false
                    }
                    mainActivityPBLoginLoading.visibility = View.VISIBLE
                    it.setBackgroundColor(resources.getColor(R.color.disable_color, null))
                    mainActivityOTFPhone.isEnabled = false
                    loginViewModel.makeLoginRequest("+91", currentText)
                } else {
                    mainActivityOTFPhone.error = "Enter valid number"
                }
            }
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        loginViewModel.getLoginResponse().observe(this) {

            if (it.statusCode == 200) {
                enableLoginButton()
                startActivity(Intent(this, SelectGiftCardActivity::class.java))
            } else {
                enableLoginButton()
                Snackbar.make(
                    binding.root,
                    it.message ?: "Unknown Error",
                    Snackbar.LENGTH_LONG
                ).setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, null))
                    .setTextColor(resources.getColor(R.color.white, null)).show()
            }
        }
    }

    private fun enableLoginButton() {
        binding.apply {
            mainActivityPBLoginLoading.visibility = View.GONE
            mainActivityLLLogin.setBackgroundColor(
                resources.getColor(
                    R.color.custom_primary_color, null
                )
            )
            mainActivityOTFPhone.isEnabled = true
        }
    }
}