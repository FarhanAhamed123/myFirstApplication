package com.example.cameraapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(
            this,LinearLayoutManager.VERTICAL,
            false
        )
        val usernames=ArrayList<ModelUser>()
        usernames.add(ModelUser("FirstName: Farhan","LastName: Ahamed","Eployee ID: 123454"))
        usernames.add(ModelUser("FirstName: mohammed","LastName: Afreedi","Eployee ID: 564578"))
        usernames.add(ModelUser("FirstName: john","LastName: Dsouza","Eployee ID: 875465"))
        usernames.add(ModelUser("FirstName: vijay","LastName: kumar","Eployee ID: 056986"))
        usernames.add(ModelUser("FirstName: rakshita","LastName: shetty","Eployee ID: 612754"))
        usernames.add(ModelUser("FirstName: prem","LastName: kumar","Eployee ID: 342875"))
        usernames.add(ModelUser("FirstName: virat","LastName: kohli","Eployee ID: 181878"))
        usernames.add(ModelUser("FirstName: sharath","LastName: reddy","Eployee ID: 983454"))
        usernames.add(ModelUser("FirstName: salman","LastName: khan","Eployee ID: 093454"))
        usernames.add(ModelUser("FirstName: jason","LastName: roy","Eployee ID: 467585"))
        usernames.add(ModelUser("FirstName: syed","LastName: aftab","Eployee ID: 675342"))



        val adapter=CustomAdapter(usernames)
        recyclerView.adapter=adapter







    }
}