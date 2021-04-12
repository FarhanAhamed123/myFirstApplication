package com.example.cameraapp1

import android.R.attr
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*


class MainActivity3 : AppCompatActivity() {
    //firebase recyclerview global variables
    lateinit var ref: DatabaseReference
    lateinit var options: FirebaseRecyclerOptions<model>
    var firstName: TextView? = null

    private lateinit var adapter: FirebaseRecyclerAdapter<model, myViewHolder>
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        //firebase recyclerview
        ref = FirebaseDatabase.getInstance().getReference("")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                val value = snapshot.getValue(model::class.java)
                val dataMap = snapshot.getValue() as HashMap<String,Any>
                for (key in dataMap.keys) {
                    val data = dataMap.get(key)
                    val userData = data as HashMap<String, model>
                  userData.get("firstName")
                    Log.d("data", userData.toString())
                }
//                firstName?.text = value?.user?.firstName

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("db error", error.toString())
            }
        })
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)




        options =
            FirebaseRecyclerOptions.Builder<model>().setQuery(ref, model::class.java).build()
        /* adapter = object : FirebaseRecyclerAdapter<model, myViewHolder>(options) {
             override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
                 val v = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.single_view_layout, parent, false)
                 return myViewHolder(v)
             }

             @SuppressLint("SetTextI18n")
             override fun onBindViewHolder(holder: myViewHolder, position: Int, model: model) {
                 holder.firstNameTextView.setText(model.firstName)
                 holder.lastNameTextView.setText(model.lastName)
                 holder.employeeIDTextView.setText("" + model.employeeID)
                 holder.dateTextView.setText(model.dateTime)

             }
         }

         this.adapter.startListening()
         recyclerView.setAdapter(adapter)
         */
    }

}






