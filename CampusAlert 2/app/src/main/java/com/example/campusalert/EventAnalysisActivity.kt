package com.example.campusalert

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class EventAnalysisActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var backBtn: Button

    val db = FirebaseFirestore.getInstance()
    val analysisList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_analysis)

        listView = findViewById(R.id.analysisList)
        backBtn = findViewById(R.id.backBtn)

        backBtn.setOnClickListener {
            finish()
        }

        loadAnalysis()
    }

    private fun loadAnalysis() {

        db.collection("events")
            .get()
            .addOnSuccessListener { events ->

                for (eventDoc in events) {

                    val title = eventDoc.getString("title")

                    db.collection("registrations")
                        .whereEqualTo("eventTitle", title)
                        .get()
                        .addOnSuccessListener { regs ->

                            val count = regs.size()

                            analysisList.add("$title\nRegistrations: $count")

                            val adapter = ArrayAdapter(
                                this,
                                android.R.layout.simple_list_item_1,
                                analysisList
                            )

                            listView.adapter = adapter
                        }
                }
            }
    }
}