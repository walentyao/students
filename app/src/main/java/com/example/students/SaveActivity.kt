package com.example.students

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
class SaveActivity : AppCompatActivity() {
    private lateinit var first_name : EditText
    private lateinit var button_save : Button
    private lateinit var button_back :Button
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
        button_back = findViewById(R.id.button_back)
        first_name.setText(intent.getStringExtra("first_name"))
        last_name.setText(intent.getStringExtra("last_name"))
        second_name.setText(intent.getStringExtra("second_name"))
        birth_day.setText(intent.getStringExtra("birth_day"))
        faculti.setText(intent.getStringExtra("faculti"))
        grup.setText(intent.getStringExtra("grup"))
        val answerIntent = Intent()
        button_save.setOnClickListener{
            if (checkAllFields()){
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
        button_back.setOnClickListener {
            finish()
        }
    }

    private fun checkAllFields(): Boolean {
        if(check(first_name.text.toString())){
            first_name.error = "Неправильно введены данные"
            return false
        }
        if(check(second_name.text.toString())){
            second_name.error = "Неправильно введены данные"
            return false
        }
        if(check(last_name.text.toString())){
            last_name.error = "Неправильно введены данные"
            return false
        }
        if(!check3(birth_day.text.toString())){
            birth_day.error ="Неправидьно введены данные"
            return false
        }
        if(check(faculti.text.toString())){
            faculti.error = "Неправильно введены данные"
            return false
        }
        if(check2(grup.text.toString())){
            grup.error = "Неправильно введены данные"
            return false
        }
        return true
    }
    private fun check(text:String):Boolean{
        return text == ""||
                text.contains(".")||
                text.contains("/")||
                text.contains(",")||
                text.contains(" ")||
                text.contains("0")||
                text.contains("1")||
                text.contains("2")||
                text.contains("3")||
                text.contains("4")||
                text.contains("5")||
                text.contains("6")||
                text.contains("7")||
                text.contains("8")||
                text.contains("9")||
                text.contains("(")||
                text.contains(")")||
                text.contains("!")||
                text.contains("@")||
                text.contains("'")||
                text.contains('"')||
                text.contains("{")||
                text.contains("}")||
                text.contains("[")||
                text.contains("]")||
                text.contains("\\")||
                text.contains("|")||
                text.contains("/")||
                text.contains("?")||
                text.contains("_")||
                text.contains("=")||
                text.contains("+")||
                text.contains("*")||
                text.contains("&")||
                text.contains("^")||
                text.contains(":")||
                text.contains("%")||
                text.contains("$")||
                text.contains(";")||
                text.contains("#")||
                text.contains("№")||
                text.contains("`")||
                text.contains("~")
    }
    private fun check2(text:String):Boolean{
        return text == ""||
                text.contains(".")||
                text.contains("/")||
                text.contains(",")||
                text.contains(" ")||
                text.contains("(")||
                text.contains(")")||
                text.contains("!")||
                text.contains("@")||
                text.contains("'")||
                text.contains('"')||
                text.contains("{")||
                text.contains("}")||
                text.contains("[")||
                text.contains("]")||
                text.contains("\\")||
                text.contains("|")||
                text.contains("/")||
                text.contains("?")||
                text.contains("_")||
                text.contains("=")||
                text.contains("+")||
                text.contains("*")||
                text.contains("&")||
                text.contains("^")||
                text.contains(":")||
                text.contains("%")||
                text.contains("$")||
                text.contains(";")||
                text.contains("#")||
                text.contains("№")||
                text.contains("`")||
                text.contains("~")
    }
    private fun check3(text:String):Boolean{
        if(text.length != 10 ||!text.contains('-')) {
            return false
        } else {
            val year = text.split("-")[0].toInt()
            val month = text.split("-")[1].toInt()
            val day = text.split("-")[2].toInt()

            if(year < 1000) {
                return false
            }
            if(month < 1 || month > 12) {
                return false
        }
            if(day < 1 || day > 31) {
                return false
        }
            return true
        }

    }
}