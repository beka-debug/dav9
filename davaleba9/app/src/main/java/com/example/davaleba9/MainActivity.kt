package com.example.davaleba9

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Matcher
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       mAuth = FirebaseAuth.getInstance();


        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.repeat_password)
        val button = findViewById<Button>(R.id.submit)

        button.setOnClickListener {
            if(!emailValidator(email = email.text.toString())){
                Toast.makeText(this,"Incorrect Email",Toast.LENGTH_LONG).show()
            }else if(isValidPassword(password.text.toString())){
                Toast.makeText(this,"Incorrect password",Toast.LENGTH_LONG).show()
            }else if(password.text.toString() != confirmPassword.text.toString()){
                Toast.makeText(this,"Passwords are not same",Toast.LENGTH_LONG).show()
            }else {
                mAuth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
            }
        }
    }


    fun emailValidator(email: String): Boolean {
        val pattern: Pattern
        val EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isValidPassword(s: String?): Boolean {
        val PASSWORD_PATTERN = Pattern.compile(
            "[a-z0-9\\!\\@\\#\\$]{9,}"
        )
        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches()
    }
}