package com.hasanchodhury.practiceforclp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val user = findViewById<EditText>(R.id.user).text.toString()
            val pass = findViewById<EditText>(R.id.pass).text.toString()
            if (user=="hasan"&&pass =="123"){
                val intent = Intent(this@MainActivity, home::class.java)
                startActivity(intent)
            } else{ Toast.makeText( this@MainActivity, "Incorrect username or password", Toast.LENGTH_SHORT).show()}
        }
    }
}