package com.android.firebase

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.firebase.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class RegistrationActivity : AppCompatActivity() {

    private var progressbar: ProgressBar? = null
    private var mAuth: FirebaseAuth? = null
    lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance()

        // initialising all views through id defined above

        progressbar = binding.progressBar

        val btn = findViewById<Button>(R.id.btnRegister)

        // Set on Click Listener on Registration button
        btn.setOnClickListener {
            registerNewUser()
            registerNewUserA()
        }
    }

    private fun registerNewUser() {

        // show the visibility of progress bar to show loading
        progressbar!!.visibility = View.VISIBLE

        // Take the value of two edit texts in Strings
       binding.email!!.text.toString()
        binding.passwd!!.text.toString()

        // Validations for input email and password
        if (TextUtils.isEmpty(binding.email.toString())) {
            Toast.makeText(applicationContext, "Please enter email!!", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(binding.passwd.toString())) {
            Toast.makeText(applicationContext, "Please enter password!!", Toast.LENGTH_LONG).show()
            return
        }

        // create new user or register new user
        try {
            mAuth!!.createUserWithEmailAndPassword(binding.email.text.toString(), binding.passwd.text.toString())
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Registration successful!", Toast.LENGTH_LONG).show()
                        // hide the progress bar
                        binding.progressBar.visibility = View.GONE
                        // if the user created intent to login activity
                        val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Registration failed
                        Toast.makeText(applicationContext, "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show()

                        // hide the progress bar
                        binding.progressBar.visibility = View.GONE
                    }
                }
        }catch (e: FirebaseAuthException){

        }

    }
    private fun registerNewUserA() {
        val email = binding.email.text.toString().trim() // Trim any leading/trailing whitespaces

        // Validate email format using a regular expression
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!email.matches(emailPattern.toRegex())) {
            Toast.makeText(applicationContext, "Please enter a valid email address!", Toast.LENGTH_LONG).show()
            return
        }
          binding.passwd.text.toString()
    }

}
