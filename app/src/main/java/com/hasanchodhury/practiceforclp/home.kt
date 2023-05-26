package com.hasanchodhury.practiceforclp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.core.view.isNotEmpty


class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var add = findViewById<Button>(R.id.add)
        var list = findViewById<ListView>(R.id.list)

        var array = ArrayList<Int>()
        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, array)
        list.adapter = arrayAdapter

        add.setOnClickListener {
            var currentInput = findViewById<EditText>(R.id.input).text.toString().toInt()
            if (list.isNotEmpty()) {
                var preInput = array.last()
                var result = currentInput + preInput
                array.add(result)
            }
            else{
                array.add(currentInput)
            }
            arrayAdapter.notifyDataSetChanged()
        }
    }
}