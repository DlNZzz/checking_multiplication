package com.example.lab1_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allNumbersButton: Button = findViewById(R.id.btn_all_numbers)
        val selectiveButton: Button = findViewById(R.id.btn_selective)
        val numberInput: EditText = findViewById(R.id.et_number)

        allNumbersButton.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("isSelective", false)
            startActivity(intent)
        }

        selectiveButton.setOnClickListener {
            val input = numberInput.text.toString()
            val number = input.toIntOrNull()

            if (number != null && number in 2..9) {
                val intent = Intent(this, ExerciseActivity::class.java)
                intent.putExtra("isSelective", true)
                intent.putExtra("selectedNumber", number)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Введите число от 2 до 9", Toast.LENGTH_SHORT).show()
            }
        }
    }
}