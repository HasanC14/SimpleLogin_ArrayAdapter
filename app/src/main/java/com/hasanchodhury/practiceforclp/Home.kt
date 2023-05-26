package com.hasanchodhury.practiceforclp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.core.view.isNotEmpty


class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val add = findViewById<Button>(R.id.add)
        val quiz = findViewById<Button>(R.id.quiz)
        val list = findViewById<ListView>(R.id.list)

        val array = ArrayList<Int>()
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, array)
        list.adapter = arrayAdapter

        add.setOnClickListener {
            val currentInput = findViewById<EditText>(R.id.input).text.toString().toInt()
            if (list.isNotEmpty()) {
                val preInput = array.last()
                val result = currentInput + preInput
                array.add(result)
            }
            else{
                array.add(currentInput)
            }
            arrayAdapter.notifyDataSetChanged()
        }
        quiz.setOnClickListener {
            val intent = Intent(this@Home, Quiz::class.java)
            startActivity(intent)
        }
    }
}