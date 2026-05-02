package com.example.campusalert

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class PostEventActivity : AppCompatActivity() {

    lateinit var title: EditText
    lateinit var description: EditText
    lateinit var date: EditText
    lateinit var postBtn: Button
    lateinit var backBtn: Button

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_event)

        title = findViewById(R.id.eventTitle)
        description = findViewById(R.id.eventDescription)
        date = findViewById(R.id.eventDate)
        postBtn = findViewById(R.id.postBtn)
        backBtn = findViewById(R.id.backBtn)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        backBtn.setOnClickListener {
            finish()
        }

        postBtn.setOnClickListener {

            val eventTitle = title.text.toString().trim()
            val eventDesc = description.text.toString().trim()
            val eventDate = date.text.toString().trim()

            // ✅ Validation
            if (eventTitle.isEmpty() || eventDesc.isEmpty() || eventDate.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val event = hashMapOf(
                "title" to eventTitle,
                "description" to eventDesc,
                "date" to eventDate
            )

            db.collection("events")
                .add(event)
                .addOnSuccessListener {
                    Toast.makeText(this, "Event Posted!", Toast.LENGTH_SHORT).show()

                    // Optional: clear fields
                    title.text.clear()
                    description.text.clear()
                    date.text.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}