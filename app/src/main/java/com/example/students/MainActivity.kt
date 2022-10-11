package com.example.students

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var students :MutableList<Student>
    private lateinit var all_students :MutableList<Student>
    private lateinit var facultets : MutableList<String?>
    private lateinit var button_add : Button
    private lateinit var button_del: Button
    private lateinit var button_change: Button
    private lateinit var button_prev : Button
    private lateinit var button_next : Button
    private lateinit var button_prev2 : Button
    private lateinit var button_next2 : Button
    private lateinit var textView : TextView
    private lateinit var textView2 : TextView
    var current : Int = 0
    var current_all :Int = 0
    var current_fac : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        all_students= mutableListOf()
        students= mutableListOf()
        facultets= mutableListOf("All")
        button_add = findViewById(R.id.button_add)
        button_del = findViewById(R.id.button_del)
        button_change = findViewById(R.id.button_change)
        button_next = findViewById(R.id.button_next)
        button_prev = findViewById(R.id.button_prev)
        button_next2 = findViewById(R.id.button_next2)
        button_prev2 = findViewById(R.id.button_prev2)
        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)
        updateTextView2(facultets[current_fac])
        button_add.setOnClickListener {
            val saveIntent = Intent(this@MainActivity,
                SaveActivity::class.java)
            startActivityForResult(saveIntent, REQUEST_CHOOSE_THIEF)
        }
        button_next.setOnClickListener {
            if (current < students.size -1){
                current += 1
                updateTextView(students[current].first_name)
            }
        }
        button_prev.setOnClickListener {
            if (current > 0){
                current -= 1
                updateTextView(students[current].first_name)
            }
        }
        button_del.setOnClickListener {

        }
        button_next2.setOnClickListener {
            if (current_fac < facultets.size -1){
                current_fac += 1
                updateStudents()
                updateTextView2(facultets[current_fac])
            }
        }
        button_prev2.setOnClickListener {
            if (current_fac > 0){
                current_fac -= 1
                updateStudents()
                updateTextView2(facultets[current_fac])
            }
        }
    }
    private fun updateTextView(text : String?){
        textView.text = text
    }
    private fun updateTextView2(text: String?){
        textView2.text = text
    }
    private fun updateStudents(){
        students = if (facultets[current_fac] == "All"){
            all_students.toMutableList()
        } else{
            all_students.filter{ it.faculti == facultets[current_fac] }.toMutableList()
        }
        current = 0
        updateTextView(students[current].first_name)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHOOSE_THIEF) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val first_name = data?.getStringExtra("first_name")
                val second_name = data?.getStringExtra("second_name")
                val last_name = data?.getStringExtra("last_name")
                val birth_day = data?.getStringExtra("birth_day")
                val faculti = data?.getStringExtra("faculti")
                val grup = data?.getStringExtra("grup")
                val student = Student(first_name,
                    second_name,last_name,birth_day,faculti,grup
                )
                all_students.add(student)
                current_all +=1
                if (!facultets.contains(student.faculti)){
                    facultets.add(student.faculti)
                }
                updateStudents()
                updateTextView(students[current].first_name)
            }
        }
    }
    companion object{
        const val REQUEST_CHOOSE_THIEF = 0
    }
}