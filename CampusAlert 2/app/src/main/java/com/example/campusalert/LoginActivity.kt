package com.example.campusalert

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {

            val userEmail = email.text.toString().trim().lowercase()

            if (userEmail == "admin@college.com") {

                Toast.makeText(this,"Admin Login",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AdminDashboardActivity::class.java))

            } else {

                Toast.makeText(this,"Student Login",Toast.LENGTH_SHORT).show()

                val intent = Intent(this, StudentDashboardActivity::class.java)
                intent.putExtra("email", userEmail)
                startActivity(intent)

            }

        }
    }
}