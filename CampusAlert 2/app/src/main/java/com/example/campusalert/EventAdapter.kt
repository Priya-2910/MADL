package com.example.campusalert

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class EventAdapter(
    private val eventList: List<Event>
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    val db = FirebaseFirestore.getInstance()

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.eventTitle)
        val date: TextView = itemView.findViewById(R.id.eventDate)
        val register: Button = itemView.findViewById(R.id.registerBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_item, parent, false)

        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        val event = eventList[position]

        holder.title.text = event.title
        holder.date.text = event.date

        holder.register.setOnClickListener {

            val data = hashMapOf(
                "eventTitle" to event.title
            )

            db.collection("registrations")
                .add(data)

            Toast.makeText(
                holder.itemView.context,
                "Registered Successfully",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}