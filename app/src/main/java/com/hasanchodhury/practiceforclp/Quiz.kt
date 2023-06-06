package com.hasanchodhury.practiceforclp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Quiz : AppCompatActivity() {
    private lateinit var questionEditText: EditText
    private lateinit var addQuestionButton: Button
    private lateinit var seeAllQuestionsButton: Button

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionEditText = findViewById(R.id.input_q)
        addQuestionButton = findViewById(R.id.add_q)
        seeAllQuestionsButton = findViewById(R.id.all_q)

        dbHelper = DatabaseHelper(this)

        addQuestionButton.setOnClickListener {
            val question = questionEditText.text.toString()

            if (question.isNotEmpty()) {
                val inserted = dbHelper.insertQuestion(question)
                if (inserted != -1L) {
                    Toast.makeText(this, "Question added successfully", Toast.LENGTH_SHORT).show()
                    questionEditText.text.clear()
                } else {
                    Toast.makeText(this, "Failed to add question", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a question", Toast.LENGTH_SHORT).show()
            }
        }

        seeAllQuestionsButton.setOnClickListener {
            val questions = dbHelper.getAllQuestions()
            Toast.makeText(this, questions, Toast.LENGTH_LONG).show()
        }
    }
}

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "questions"
        private const val COLUMN_QUESTION = "question"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUMN_QUESTION TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertQuestion(question: String): Long {
        val values = ContentValues()
        values.put(COLUMN_QUESTION, question)

        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllQuestions(): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val stringBuilder = StringBuilder()

        val columnIndexQuestion = cursor.getColumnIndex(COLUMN_QUESTION)

        while (cursor.moveToNext()) {
            if (columnIndexQuestion >= 0) {
                val question = cursor.getString(columnIndexQuestion)
                stringBuilder.append("Question: $question\n")
            } else {
                // Handle the case where the column index is not found
                // You can log an error message or handle it as per your requirement
            }
        }

        cursor.close()
        return stringBuilder.toString()
    }
}