package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.LinearLayout
import android.view.View
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var intro = "Welcome to the Awesome Quiz App! Test your knowledge on"
        val textView: TextView = findViewById(R.id.introTextView)
        textView.text = intro


        // Get references to the UI elements
        //val appDescriptionTextView: TextView = findViewById(R.id.introTextView)
        val startButton: Button = findViewById(R.id.startButton)

        // Set the app description
        //appDescriptionTextView.text = "Welcome to the Awesome Quiz App! Test your knowledge on "

        // Set an OnClickListener for the start button
        startButton.setOnClickListener {
            Log.d("MainActivity", "Start button clicked!") // Add this line
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
    }
