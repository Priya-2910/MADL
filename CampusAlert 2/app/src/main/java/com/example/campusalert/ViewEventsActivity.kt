package com.example.campusalert

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ViewEventsActivity : AppCompatActivity() {

    lateinit var recycler: RecyclerView
    lateinit var backBtn: Button

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_events)

        recycler = findViewById(R.id.eventsRecycler)
        backBtn = findViewById(R.id.backBtn)

        backBtn.setOnClickListener {
            finish()
        }

        loadEvents()
    }

    private fun loadEvents() {

        db.collection("events")
            .get()
            .addOnSuccessListener { result ->

                val eventList = ArrayList<Event>()

                for (document in result) {

                    val event = document.toObject(Event::class.java)
                    eventList.add(event)

                }

                recycler.layoutManager = LinearLayoutManager(this)
                recycler.adapter = EventAdapter(eventList)
            }
    }
}