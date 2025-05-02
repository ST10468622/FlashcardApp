package com.example.flashcardapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {


        // Declare class properties for the views
        private lateinit var questionTextView: TextView
        private lateinit var trueButton: Button
        private lateinit var falseButton: Button
        private lateinit var resultTextView: TextView
        private lateinit var reviewButton: Button

        // Quiz data:  Parallel arrays for questions and answers.  The original code had the question text and boolean answer combined in one String.  I've separated them.
        private val quizQuestions = arrayOf(
            "Apartheid in South Africa officially began in 1948.",
            "Nelson Mandela was released from prison in 1980.",
            "The African National Congress (ANC) was banned during apartheid.",
            "The apartheid system allowed all races to vote equally.",
            "The Sharpeville Massacre occurred in 1960.",
        )
        private val quizAnswers = booleanArrayOf(true, false, true, false, true) // Corrected the answers to match the questions

        private var currentQuestionIndex = 0
        private var score = 0
        private val userAnswers = mutableListOf<Boolean?>() // Store user's boolean answers (true/false)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.d("QuizActivity", "QuizActivity onCreate called!")
            setContentView(R.layout.activity_quiz) // Make sure this layout file exists

            // Initialize the views using findViewById()
            questionTextView = findViewById(R.id.questionTextView)
            trueButton = findViewById(R.id.trueButton)
            falseButton = findViewById(R.id.falseButton)
            resultTextView = findViewById(R.id.resultTextView)
            reviewButton = findViewById(R.id.reviewButton) // Find the review button

            // Set click listeners for the answer buttons
            trueButton.setOnClickListener { handleAnswerSelection(true) }
            falseButton.setOnClickListener { handleAnswerSelection(false) }
            reviewButton.setOnClickListener { showReview() } //set listener for review button

            // Display the first question
            displayQuestion()
            reviewButton.visibility = Button.GONE //hide review button initially.
        }

        private fun displayQuestion() {
            // Safety check
            if (currentQuestionIndex < quizQuestions.size) {
                val currentQuestion = quizQuestions[currentQuestionIndex]
                questionTextView.text = currentQuestion
                // Enable buttons
                trueButton.isEnabled = true
                falseButton.isEnabled = true
                resultTextView.text = "" // Clear any previous result
            } else {
                // Quiz is finished, show results
                showResults()
            }
        }

        private fun handleAnswerSelection(answer: Boolean) {
            // Store the user's answer
            userAnswers.add(answer)

            // Disable buttons after an answer.  The original code had these reversed.
            trueButton.isEnabled = false
            falseButton.isEnabled = false

            val correctAnswer = quizAnswers[currentQuestionIndex]
            if (answer == correctAnswer) {
                score++
                resultTextView.text = "Correct!"
            } else {
                resultTextView.text = "Incorrect!"
            }

            currentQuestionIndex++
            displayQuestion() //show next question.
        }

        private fun showResults() {
            var resultString = "Quiz Finished!\nYour Score: $score out of ${quizQuestions.size}\n\n"

            if (score >= quizQuestions.size / 2) { // Changed the condition.
                resultString += "Great job!"
            } else {
                resultString += "Keep practicing!"
            }

            resultTextView.text = resultString
            trueButton.visibility = Button.GONE
            falseButton.visibility = Button.GONE
            reviewButton.visibility = Button.VISIBLE // Make review button visible
        }

        private fun showReview() {
            var reviewString = "Review of Questions and Answers:\n\n"
            for (i in quizQuestions.indices) {
                reviewString += "Question ${i + 1}: ${quizQuestions[i]}\n"
                reviewString += "Your Answer: "
                reviewString += if (userAnswers[i] != null) {
                    if (userAnswers[i] == true) "True" else "False"
                } else "Not Answered"
                reviewString += "\nCorrect Answer: ${if (quizAnswers[i]) "True" else "False"}\n\n"
            }
            //Use a toast to display the review.  Could also use a dialog, or a new activity.
            Toast.makeText(this, reviewString, Toast.LENGTH_LONG).show()
        }
    }

