package com.example.cameraapp1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.cameraapp1.R.id.inputLastName
import com.example.cameraapp1.R.id.presentid

class MainActivity2 : AppCompatActivity() {
    //sharedpreference global variable(attendance)
    private lateinit var preferencesProvider:PreferencesProvider
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
                             //sharedpreference function(attendance)
        preferencesProvider= PreferencesProvider(applicationContext)

        val saveButon:Button=findViewById(R.id.buttonSave)
        saveButon.setOnClickListener {
            val firstName:EditText=findViewById(R.id.inputFirstName)
            preferencesProvider.putString(Constants.KEY_FIRST_NAME,firstName.text.toString())
            val lastName:EditText=findViewById(inputLastName)
            preferencesProvider.putString(Constants.KEY_LAST_NAME,lastName.text.toString())
            val employeeID:EditText=findViewById(R.id.inputEmployeeID)
            preferencesProvider.putInt(Constants.KEY_EMPLOYEEID,employeeID.text.toString().toInt())
            val radioGroup:RadioGroup=findViewById(R.id.radioGroupPresent)
            preferencesProvider.putBoolean(Constants.KEY_PRESENT, radioGroup.checkedRadioButtonId==R.id.radioButtonYes)
            Toast.makeText(applicationContext,"Data Saved",Toast.LENGTH_SHORT).show()
        }
        val loadButton:Button=findViewById(R.id.buttonLoad)
        loadButton.setOnClickListener {
            val firstName:EditText=findViewById(R.id.inputFirstName)
            firstName.setText(preferencesProvider.getString(Constants.KEY_FIRST_NAME))
            val lastName:EditText=findViewById(inputLastName)
            lastName.setText(preferencesProvider.getString(Constants.KEY_LAST_NAME))
            val employeeID:EditText=findViewById(R.id.inputEmployeeID)
            employeeID.setText(preferencesProvider.getInt(Constants.KEY_EMPLOYEEID).toString())
            if (preferencesProvider.getBoolean(Constants.KEY_PRESENT)){
                val radioButton1:RadioButton=findViewById(R.id.radioButtonYes)
                radioButton1.isChecked=true
            }else{
                val radioButton2:RadioButton=findViewById(R.id.radioButtonNo)
                radioButton2.isChecked=true
            }
            Toast.makeText(applicationContext,"Data Retrieved",Toast.LENGTH_SHORT).show()
        }

        val clearButton:Button=findViewById(R.id.buttonClear)
        clearButton.setOnClickListener {
            preferencesProvider.clear()
            Toast.makeText(applicationContext,"Data Cleared",Toast.LENGTH_SHORT).show()
        }

        //view attendance(recycleView)

        val viewAttendance:Button=findViewById(R.id.viewAttendanceButton)
        viewAttendance.setOnClickListener {
            val intent= Intent(getBaseContext(),MainActivity3::class.java)
            startActivity(intent)

        }

    }
}