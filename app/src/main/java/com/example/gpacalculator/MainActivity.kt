package com.example.gpacalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var subjects : ArrayList<String> = ArrayList<String>()
    private var grades : ArrayList<String> = ArrayList<String>()
    private var credits : ArrayList<String> = ArrayList<String>()
    private var gradeLetters: ArrayList<String> = ArrayList<String>()
    private var gradePoints : ArrayList<String> = ArrayList<String>()
    private var credit: Int = 0
    private var GPA: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextSubject = findViewById<EditText>(R.id.editTextSubject)
        val editTextGrade = findViewById<EditText>(R.id.editTextGrade)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnComputeGPA = findViewById<Button>(R.id.btnComputeGPA)
        val spinner = findViewById<Spinner>(R.id.ddlCredits)

        val choices = resources.getStringArray(R.array.choices)
        var spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, choices)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                credit = 0
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when {
                    parent.getItemAtPosition(position).toString().equals("2") -> {
                        credit = 2
                    }
                    parent.getItemAtPosition(position).toString().equals("4") -> {
                        credit = 4
                    }
                    parent.getItemAtPosition(position).toString().equals("5") -> {
                        credit = 5
                    }
                }
            }
        }

        btnAdd.setOnClickListener {
            subjects.add(editTextSubject.text.toString())
            grades.add(editTextGrade.text.toString())
            credits.add(credit.toString())

            editTextSubject.text.clear()
            editTextGrade.text.clear()


            Toast.makeText(this, "Value has been successfully added.", Toast.LENGTH_SHORT).show()
        }

        btnComputeGPA.setOnClickListener{
            var index = 0
            var totalCredits = 0
            var totalGradePoints = 0.0
            while(index < grades.size) {
                var gradeLetter = getGradeLetter(grades.get(index).toDouble())
                gradeLetters.add(gradeLetter)

                var gradePoint = getGradePoint(gradeLetter)
                gradePoints.add(gradePoint)

                totalCredits += credits.get(index).toInt()
                totalGradePoints += credits.get(index).toInt() * gradePoint.toDouble()

                index++
            }
            GPA = (totalGradePoints / totalCredits).toString()


            var intent = Intent(this, GPAPredictionActivity::class.java)
            intent.putExtra("SUBJECTS", subjects)
            intent.putExtra("GRADELETTERS", gradeLetters)
            intent.putExtra("GPA", GPA)
            startActivity(intent)
        }

    }

    private fun getGradeLetter(percentageGrade: Double) : String {
        var gradeLetter = ""
        if (percentageGrade >= 97 && percentageGrade <= 100) {
            gradeLetter = "A+"
        } else if (percentageGrade >= 93 && percentageGrade <= 96) {
            gradeLetter = "A"
        } else if (percentageGrade >= 90 && percentageGrade <= 92) {
            gradeLetter = "A-"
        } else if (percentageGrade >= 87 && percentageGrade <= 89) {
            gradeLetter = "B+"
        } else if (percentageGrade >= 83 && percentageGrade <= 86) {
            gradeLetter = "B"
        } else if (percentageGrade >= 97 && percentageGrade <= 100) {
            gradeLetter = "B-"
        }
        return gradeLetter
    }

    private fun getGradePoint(gradeLetter : String) : String {
        var gradePoint = 0.0
        when {
            gradeLetter == "A+" -> {
                gradePoint = 4.3
            }
            gradeLetter == "A" -> {
                gradePoint = 4.0
            }
            gradeLetter == "A-" -> {
                gradePoint = 3.7
            }
            gradeLetter == "B+" -> {
                gradePoint = 3.3
            }
            gradeLetter == "B" -> {
                gradePoint = 3.0
            }
            gradeLetter == "B-" -> {
                gradePoint = 2.7
            }
            gradeLetter == "F" -> {
                gradePoint = 0.0
            }
        }
        return gradePoint.toString()
    }
}
