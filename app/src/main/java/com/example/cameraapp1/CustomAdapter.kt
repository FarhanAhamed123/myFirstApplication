package com.example.cameraapp1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val userDataList:ArrayList<ModelUser>):RecyclerView.Adapter
<CustomAdapter.ViewHolder>(){
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bindData(userModel:ModelUser){
            val firstNameTextView=itemView.findViewById<TextView>(R.id.firstNameTextView)
            val lastNameTextView=itemView.findViewById<TextView>(R.id.lastNameTextView)
            val employeeIDTextView=itemView.findViewById<TextView>(R.id.employeeIDTextView)

            firstNameTextView.text=userModel.firstName
            lastNameTextView.text=userModel.lastName
            employeeIDTextView.text=userModel.employeeID

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
val view=LayoutInflater.from(parent.context)
    .inflate(R.layout.item,parent,false)
    return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
holder.bindData(userDataList[position])    }

    override fun getItemCount(): Int {
return userDataList.size       }

}
