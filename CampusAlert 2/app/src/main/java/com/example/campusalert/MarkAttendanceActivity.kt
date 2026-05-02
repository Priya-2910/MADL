package com.example.campusalert

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MarkAttendanceActivity : AppCompatActivity() {

    lateinit var studentEmail: EditText
    lateinit var subjectName: EditText
    lateinit var radioGroup: RadioGroup
    lateinit var submitBtn: Button

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark_attendance)

        studentEmail = findViewById(R.id.studentEmail)
        subjectName = findViewById(R.id.subjectName)
        radioGroup = findViewById(R.id.attendanceGroup)
        submitBtn = findViewById(R.id.submitAttendance)

        submitBtn.setOnClickListener {

            val email = studentEmail.text.toString()
            val subject = subjectName.text.toString()

            val selectedId = radioGroup.checkedRadioButtonId
            val radioBtn = findViewById<RadioButton>(selectedId)

            val status = radioBtn.text.toString()

            val attendance = hashMapOf(
                "status" to status
            )

            db.collection("attendance")
                .document(subject)
                .collection("students")
                .document(email)
                .set(attendance)
                .addOnSuccessListener {

                    Toast.makeText(this,"Attendance Updated",Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {

                    Toast.makeText(this,"Error updating attendance",Toast.LENGTH_SHORT).show()

                }
        }
    }
}