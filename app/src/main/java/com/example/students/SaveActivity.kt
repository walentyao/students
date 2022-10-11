package com.example.students

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
class SaveActivity : AppCompatActivity() {
    private lateinit var first_name : EditText
    private lateinit var button_save : Button
    private lateinit var second_name : EditText
    private lateinit var last_name : EditText
    private lateinit var birth_day : EditText
    private lateinit var faculti : EditText
    private lateinit var grup : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)
        first_name = findViewById(R.id.first_name)
        second_name = findViewById(R.id.second_name)
        last_name = findViewById(R.id.last_name)
        birth_day = findViewById(R.id.birth_day)
        faculti = findViewById(R.id.faculti)
        grup = findViewById(R.id.grup)
        button_save = findViewById(R.id.button_save)
        val answerIntent = Intent()
        button_save.setOnClickListener{
            if (first_name.text.toString() != ""){
                answerIntent.putExtra("first_name", first_name.text.toString())
                answerIntent.putExtra("second_name", second_name.text.toString())
                answerIntent.putExtra("last_name", last_name.text.toString())
                answerIntent.putExtra("birth_day", birth_day.text.toString())
                answerIntent.putExtra("faculti", faculti.text.toString())
                answerIntent.putExtra("grup", grup.text.toString())
                setResult(RESULT_OK, answerIntent)
                finish()
            }
        }
    }

}