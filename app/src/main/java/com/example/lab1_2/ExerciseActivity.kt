package com.example.lab1_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ExerciseActivity : AppCompatActivity() {

    private var correctAnswers = 0
    private var totalQuestions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val questionText: TextView = findViewById(R.id.tv_question)
        val answerInput: EditText = findViewById(R.id.et_answer)
        val checkButton: Button = findViewById(R.id.btn_check)

        val isSelective = intent.getBooleanExtra("isSelective", false)
        val selectedNumber = intent.getIntExtra("selectedNumber", 0)

        var currentQuestion = generateQuestion(isSelective, selectedNumber)
        questionText.text = currentQuestion

        checkButton.setOnClickListener {
            val userAnswer = answerInput.text.toString().toIntOrNull()
            if (userAnswer != null) {
                val correctAnswer = getCorrectAnswer(currentQuestion)

                if (userAnswer == correctAnswer) {
                    Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT).show()
                    correctAnswers++
                } else {
                    Toast.makeText(this, "Неверный ответ", Toast.LENGTH_SHORT).show()
                }

                totalQuestions++

                if (totalQuestions < 20) {
                    currentQuestion = generateQuestion(isSelective, selectedNumber)
                    questionText.text = currentQuestion
                    answerInput.text.clear()
                } else {
                    val percentage = (correctAnswers * 100) / totalQuestions
                    Toast.makeText(
                        this,
                        "Тест завершен! Правильных ответов: $percentage%",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Введите число", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateQuestion(isSelective: Boolean, selectedNumber: Int): String {
        val number1 = if (isSelective) selectedNumber else Random.nextInt(2, 10)
        val number2 = Random.nextInt(2, 10)
        return "$number1 x $number2"
    }

    private fun getCorrectAnswer(question: String): Int {
        val parts = question.split("x")
        val number1 = parts[0].trim().toInt()
        val number2 = parts[1].trim().toInt()
        return number1 * number2
    }
}
