package com.example.cameraapp1

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.*

// camera variable
private const val REQUEST_CODE=42

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    // Location object
    companion object{
        private val REQUEST_PERMISSION_REQUEST_CODE=2020
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // camera
        val cameraButton: Button = findViewById(R.id.Button01)
        cameraButton.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Unable To Open Camera", Toast.LENGTH_LONG).show()
            }
        }


        // Location
        val locationButton:Button=findViewById(R.id.btnGetLocation)
        locationButton.setOnClickListener {
            //check permissions
            if (ContextCompat.checkSelfPermission(
                    applicationContext,android.Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this@MainActivity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                , REQUEST_PERMISSION_REQUEST_CODE)
            }else{
                //progressbar(for location visibility)

                var addressTextView:TextView=findViewById(R.id.tvAddress)
                var latitudeTextView:TextView=findViewById(R.id.tvLatitude)
                var longitudeTextView:TextView=findViewById(R.id.tvLongitude)
                var progressBar:ProgressBar=findViewById(R.id.loader)

                addressTextView.text=""
                latitudeTextView.text=""
                longitudeTextView.text=""

                progressBar.visibility=View.VISIBLE

                    //locationfunction
                getCurrentLocation()
            }

        }

        //button to goto another activity(attendance feature)
        val attendanceButton: Button=findViewById(R.id.Button04)
        attendanceButton.setOnClickListener{
            val intent = Intent(getBaseContext(), MainActivity2::class.java)

            startActivity(intent)
        }
        //button to view photos(recycler view 2)
        val viewphotos:Button=findViewById(R.id.Button03)
        viewphotos.setOnClickListener {
            val intent=Intent(getBaseContext(),MainActivity4::class.java)
            startActivity(intent)
        }

    }



          //cameraFunction
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode== REQUEST_CODE && resultCode== Activity.RESULT_OK){
            val takenImage=data?.extras?.get("data") as Bitmap
            val imageView:ImageView=findViewById(R.id.imageView01)
            imageView.setImageBitmap(takenImage)
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    //location function
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== REQUEST_PERMISSION_REQUEST_CODE && grantResults.size>0){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation()
            }else{
                Toast.makeText(this@MainActivity,"Permission Denied",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentLocation(){
        var locationRequest=LocationRequest()
        locationRequest.interval=10000
        locationRequest.fastestInterval=5000
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY

        //now getting address from latitude and longitude
        val geocoder=Geocoder(this@MainActivity, Locale.getDefault())
        var address:List<Address>

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        LocationServices.getFusedLocationProviderClient(this@MainActivity)
                .requestLocationUpdates(locationRequest,object:LocationCallback(){
                    override fun onLocationResult(locationResult: LocationResult?){
                        super.onLocationResult(locationResult)
                        LocationServices.getFusedLocationProviderClient(this@MainActivity)
                                .removeLocationUpdates(this)
                        if (locationResult !=null && locationResult.locations.size > 0){
                            var locIndex=locationResult.locations.size-1

                            var latitude=locationResult.locations.get(locIndex).latitude
                            var longitude=locationResult.locations.get(locIndex).longitude
                            var latitudeTextView:TextView=findViewById(R.id.tvLatitude)
                            latitudeTextView.text="Latitude:"+latitude
                            var longitudeTextView:TextView=findViewById(R.id.tvLongitude)
                            longitudeTextView.text="Longitude:"+longitude

                            var addresses = geocoder.getFromLocation(latitude, longitude, 1)

                            var address:String=addresses[0].getAddressLine(0)
                            var addressTextView:TextView=findViewById(R.id.tvAddress)
                            addressTextView.text=address
                            //progressbarfunction(locaion)
                            var progressBar:ProgressBar=findViewById(R.id.loader)
                            if (addressTextView!=null){
                                progressBar.visibility=View.GONE
                            }


                        }
                    }
                }, Looper.getMainLooper() )
    }



}