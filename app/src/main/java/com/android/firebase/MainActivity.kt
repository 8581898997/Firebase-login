package com.android.firebase


import android.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.firebase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var geeksforgeeks: TextView? = null
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialising all views through id defined above
        geeksforgeeks?.text = "GeeksForGeeks(Firebase Authentication)"
    }
}
