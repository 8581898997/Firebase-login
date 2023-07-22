package com.android.firebase


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.firebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private var emailTextView: EditText? = null
    private var passwordTextView: EditText? = null
    private var btn: Button? = null
    private var progressbar: ProgressBar? = null
    private var mAuth: FirebaseAuth? = null
    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // taking instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance()

        // initialising all views through id defined above
        emailTextView = binding.email
        passwordTextView = binding.password
        btn = binding.login
        progressbar = binding.progressBar

        // Set on Click Listener on Sign-in button
        btn!!.setOnClickListener { loginUserAccount() }
    }

    private fun loginUserAccount() {

        // show the visibility of progress bar to show loading
        progressbar!!.visibility = View.VISIBLE

        // Take the value of two edit texts in Strings
        val email: String
        val password: String
        email = emailTextView!!.text.toString()
        password = passwordTextView!!.text.toString()

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(
                applicationContext,
                "Please enter email!!",
                Toast.LENGTH_LONG
            )
                .show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(
                applicationContext,
                "Please enter password!!",
                Toast.LENGTH_LONG
            )
                .show()
            return
        }

        // signin existing user
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Login successful!!",
                        Toast.LENGTH_LONG
                    )
                        .show()

                    // hide the progress bar
                    binding.progressBar.visibility = View.GONE

                    // if sign-in is successful
                    // intent to home activity
                    val intent = Intent(
                        this@LoginActivity,
                        MainActivity::class.java
                    )
                    startActivity(intent)
                } else {

                    // sign-in failed
                    Toast.makeText(
                        applicationContext,
                        "Login failed!!",
                        Toast.LENGTH_LONG
                    )
                        .show()

                    // hide the progress bar
                    progressbar!!.visibility = View.GONE
                }
            }
    }
}
