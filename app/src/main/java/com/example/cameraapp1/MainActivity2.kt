package com.example.cameraapp1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.cameraapp1.R.id.*
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


     class MainActivity2 : AppCompatActivity(),View.OnClickListener {
         //sharedpreference global variable(attendance)
         private lateinit var preferencesProvider: PreferencesProvider

         @SuppressLint("CutPasteId", "SimpleDateFormat")
         override fun onCreate(savedInstanceState: Bundle?) {
             super.onCreate(savedInstanceState)
             setContentView(R.layout.activity_main2)

             //sharedpreference function(attendance)
             preferencesProvider = PreferencesProvider(applicationContext)


             //firebase(database)
             val database= FirebaseDatabase.getInstance().reference
             val saveButon: Button = findViewById(R.id.buttonSave)
             val timeButton:Button=findViewById(R.id.btn_timepicker)

             timeButton.setOnClickListener {
                 val cal = Calendar.getInstance().getTime()
                 val currentLocalTime = cal.getTime()
                 val date = SimpleDateFormat("dd-MM-yyyy HH:mm a")
                 val myLocalTime = date.format(currentLocalTime)
                 val timeTextView: TextView = findViewById(R.id.tv_textTime)
                 timeTextView.text=myLocalTime.toString()
             }

             saveButon.setOnClickListener {
                 //edittext
                 var inputFirstName:EditText=findViewById(inputFirstName)
                 var firstName=inputFirstName.text.toString()
                 var inputLastName:EditText=findViewById(inputLastName)
                 var lastName=inputLastName.text.toString()
                 var inputEmployeeID:EditText=findViewById(inputEmployeeID)
                 var employeeID=inputEmployeeID.text.toString().toInt()
                 //textview
                 val timeTextView: TextView = findViewById(R.id.tv_textTime)
                 var dateTime = timeTextView.text.toString()
                 Log.d("myTime",dateTime.toString())


                 database.setValue(Employee(firstName,lastName,employeeID, dateTime))

                 Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show()
                 loadData()//calling loadData function


             }

             //view attendance(recycleView)
             val viewAttendance: Button =
                 findViewById(R.id.viewAttendanceButton)
             viewAttendance.setOnClickListener {
                 val intent = Intent(
                    this,
                     MainActivity3::class.java
                 )
                 startActivity(intent)

             }

         }


         //this function loades the information of firstname,lastname and employee id(this functions helps to stay information as it is even when we exit app)
         private fun loadData() {
             val firstName: EditText = findViewById(R.id.inputFirstName)
             firstName.setText(preferencesProvider.getString(Constants.KEY_FIRST_NAME))
             val lastName: EditText = findViewById(inputLastName)
             lastName.setText(preferencesProvider.getString(Constants.KEY_LAST_NAME))
             val employeeID: EditText = findViewById(R.id.inputEmployeeID)
             employeeID.setText(
                 preferencesProvider.getInt(Constants.KEY_EMPLOYEEID).toString())
             if (preferencesProvider.getBoolean(Constants.KEY_PRESENT)) {
                 val radioButtonYes: RadioButton = findViewById(R.id.radioButtonYes)
                 radioButtonYes.isChecked = true
             } else {
                 val radioButtonNo: RadioButton = findViewById(R.id.radioButtonNo)
                 radioButtonNo.isChecked = true
             }
         }



         //validation(to show error msg if edit text is saved when it is empty)
         private fun validate(): Boolean {
             val firstName: EditText = findViewById(R.id.inputFirstName)
             val lastName: EditText = findViewById(R.id.inputLastName)
             val employeeID: EditText = findViewById(R.id.inputEmployeeID)
             if (firstName.text.toString().isEmpty()) {
                 firstName.error = "first name should not be blank"
                 return false
             } else if (lastName.text.toString().isEmpty()) {
                 lastName.error = "last name should not be blank"
                 return false
             } else if (employeeID.text.toString().isEmpty()) {
                 employeeID.error = "employee id should not be blank"
                 return false
             }
             return true
         }
         override fun onClick(v: View?) {
             when (v?.id) {
                 R.id.buttonSave-> {
                     if (validate()) {
                         Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()
                     }
                 }
             }
         }

     }









