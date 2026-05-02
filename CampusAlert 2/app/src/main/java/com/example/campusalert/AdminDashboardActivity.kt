package com.example.campusalert

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val postEventBtn = findViewById<Button>(R.id.postEventBtn)
        val viewAttendanceBtn = findViewById<Button>(R.id.viewAttendanceBtn)
        val approveLeaveBtn = findViewById<Button>(R.id.approveLeaveBtn)
        val analysisBtn = findViewById<Button>(R.id.analysisBtn)

        analysisBtn.setOnClickListener {

            startActivity(Intent(this, EventAnalysisActivity::class.java))

        }
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)

        logoutBtn.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        // Post Event screen
        postEventBtn.setOnClickListener {
            startActivity(Intent(this, PostEventActivity::class.java))
        }

        // View Attendance screen
        viewAttendanceBtn.setOnClickListener {
            startActivity(Intent(this, MarkAttendanceActivity::class.java))
        }

        // Approve Leave screen
        approveLeaveBtn.setOnClickListener {
            startActivity(Intent(this, ApproveLeaveActivity::class.java))
        }
    }
}