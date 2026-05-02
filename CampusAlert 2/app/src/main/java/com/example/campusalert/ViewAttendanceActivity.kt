package com.example.campusalert

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ViewAttendanceActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var backBtn: Button

    private val db = FirebaseFirestore.getInstance()
    private val items = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_attendance)

        // Initialize views
        listView = findViewById(R.id.attendanceList)
        backBtn = findViewById(R.id.backBtn)

        // Back button click
        backBtn.setOnClickListener {
            finish()
        }

        // Top arrow (ActionBar back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val studentEmail = intent.getStringExtra("email")

        if (studentEmail != null) {
            loadAttendance(studentEmail)
        }
    }

    private fun loadAttendance(studentEmail: String) {

        db.collection("attendance")
            .get()
            .addOnSuccessListener { subjects ->

                for (subject in subjects) {

                    val subjectName = subject.id

                    db.collection("attendance")
                        .document(subjectName)
                        .collection("students")
                        .document(studentEmail)
                        .get()
                        .addOnSuccessListener { doc ->

                            if (doc.exists()) {
                                val status = doc.getString("status")
                                items.add("$subjectName : $status")
                                updateList()
                            }
                        }
                }
            }
    }

    private fun updateList() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items
        )
        listView.adapter = adapter
    }

    // Handles top-left arrow button
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}