package com.example.gpacalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import java.util.ArrayList

class GPAPredictionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpaprediction)

        var subjects = intent.getStringArrayListExtra("SUBJECTS")
        var gradeLetters = intent.getStringArrayListExtra("GRADELETTERS")
        var GPA: String? = intent.getStringExtra("GPA")

        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        var resultText = "Course | Grade Letter\n"

        var index = 0
        while(index < subjects.size) {
            resultText += subjects.get(index).toString() + "  | " + gradeLetters.get(index) + "\n"
            index++
        }

        resultText += "GPA = " + GPA.toString()

        textViewResult.text = resultText

    }
}
