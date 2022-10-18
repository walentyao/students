package com.example.students

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.os.trace

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
    var current_fac : Int = 0
    var change : Boolean = false
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
            startActivityForResult(saveIntent, REQUEST_CHOOSE)
        }
        button_change.setOnClickListener {
            val intent = Intent(this@MainActivity, SaveActivity::class.java)
            intent.putExtra("first_name", students[current].first_name)
            intent.putExtra("second_name", students[current].second_name)
            intent.putExtra("last_name", students[current].last_name)
            intent.putExtra("birth_day", students[current].birth_day)
            intent.putExtra("faculti", students[current].faculti)
            intent.putExtra("grup", students[current].group)
            startActivityForResult(intent, REQUEST_CHOOSE)
            change = true
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
            if (all_students.size > 0) {
                all_students.remove(students[current])
                students.remove(students[current])
            if (students.size > 0){
                current = 0
                updateTextView(students[current].first_name)

            }else {
                if (current_fac > 0){
                    current_fac = 0
                    current = 0
                    updateFaculti()
                    updateStudents()
                    updateTextView2(facultets[current_fac])
                    if (students.size > 0) {
                        updateTextView(students[current].first_name)
                    }
                    else{
                        updateTextView("")
                    }
            }
                else{
                    updateFaculti()
                    updateTextView("")
                }
            }
                }
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
    private fun updateFaculti(){
        val new_list = mutableListOf<String?>("All")
        for (item in all_students){
            new_list.add(item.faculti)
        }
        facultets = new_list
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
        if(all_students.size!=0){
            updateTextView(students[current].first_name)
        }
        else{
            updateTextView("")
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHOOSE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                if (!change) {
                    val first_name = data?.getStringExtra("first_name")
                    val second_name = data?.getStringExtra("second_name")
                    val last_name = data?.getStringExtra("last_name")
                    val birth_day = data?.getStringExtra("birth_day")
                    val faculti = data?.getStringExtra("faculti")
                    val grup = data?.getStringExtra("grup")
                    val student = Student(
                        first_name,
                        second_name, last_name, birth_day, faculti, grup
                    )
                    all_students.add(student)
                    if (!facultets.contains(student.faculti)) {
                        facultets.add(student.faculti)
                    }
                    updateStudents()
                    updateTextView(students[current].first_name)
                }
                else{
                    val first_name = data?.getStringExtra("first_name")
                    val second_name = data?.getStringExtra("second_name")
                    val last_name = data?.getStringExtra("last_name")
                    val birth_day = data?.getStringExtra("birth_day")
                    val faculti = data?.getStringExtra("faculti")
                    val grup = data?.getStringExtra("grup")
                    val student = Student(first_name,
                        second_name,last_name,birth_day,faculti,grup
                    )
                    all_students.remove(students[current])
                    all_students.add(student)
                    students[current]=student
                    if (!facultets[current_fac].equals(student.faculti)&&current_fac!=0){
                        facultets[current_fac]=student.faculti
                    }
                    updateTextView(students[current].first_name)
                    updateTextView2(facultets[current_fac])
                    change = false
                }
            }
            }
        }
    companion object{
        const val REQUEST_CHOOSE = 0
    }
}