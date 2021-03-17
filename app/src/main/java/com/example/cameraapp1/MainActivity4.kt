package com.example.cameraapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val images= listOf<Image>(
            Image(R.drawable.img1),
            Image(R.drawable.img2),
            Image(R.drawable.img3),
            Image(R.drawable.img4),
            Image(R.drawable.img5),
            Image(R.drawable.img3),
            Image(R.drawable.img1),
            Image(R.drawable.img8)
        )
        val recyclerView:RecyclerView=findViewById(R.id.imagesRecyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter=ImageAdapter(this,images)
    }
}