package com.example.project_sqllite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sqllite.databinding.ActivityLoginBinding
import com.example.project_sqllite.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var dbHelper: databaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = databaseHelper(this)

        binding.btnSignUp.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            signupDatabase(username,password)
        }

        binding.textView5.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun signupDatabase(username : String, password : String){
        val insertRowId = dbHelper.insertUser(username, password)

        if(username.isEmpty() && password.isEmpty()){
            showError("Usename and Password cannot be empty!")
            return
        }

        if(insertRowId != -1L){
            Toast.makeText(this, "SignUp Successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, "SignUp Failed!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
