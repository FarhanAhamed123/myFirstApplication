package com.example.cameraapp1

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



 var firstNameTextView:TextView=itemView.findViewById(R.id.firstNameTextView)
 var lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)
 var employeeIDTextView: TextView = itemView.findViewById(R.id.employeeIDTextView)
 var dateTextView: TextView = itemView.findViewById(R.id.dateTextView)


}




