package com.example.campusalert

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class StudentDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)
        FirebaseMessaging.getInstance().subscribeToTopic("events")
        val eventsBtn = findViewById<Button>(R.id.btnEvents)
        val attendanceBtn = findViewById<Button>(R.id.btnAttendance)

        val studentEmail = intent.getStringExtra("email")

        eventsBtn.setOnClickListener {

            val intent = Intent(this, ViewEventsActivity::class.java)
            startActivity(intent)

        }

        attendanceBtn.setOnClickListener {

            val intent = Intent(this, ViewAttendanceActivity::class.java)
            intent.putExtra("email", studentEmail)
            startActivity(intent)

        }
    }
}