package com.example.cameraapp1

import android.content.Context

@Suppress("UNREACHABLE_CODE")
class PreferencesProvider(context: Context) {
    private val sharedPreferences=context.getSharedPreferences("myPreferences",0)

    //save and retrieve boolean values into the sharedpreferences
    fun putBoolean(key:String,value:Boolean){
        sharedPreferences.edit().putBoolean(key, value).apply()
    }
    fun getBoolean(key: String):Boolean{
        return sharedPreferences.getBoolean(key,false)
    }

    //for string values
    fun putString(key:String,value:String){
        sharedPreferences.edit().putString(key, value).apply()
    }
    fun getString(key: String):String?{
        return sharedPreferences.getString(key,null)
    }

    //for integer values
    fun putInt(key:String,value:Int){
        sharedPreferences.edit().putInt(key,value).apply()
    }
    fun getInt(key: String):Int{
        return sharedPreferences.getInt(key,0)

    }
    //clear shared preferences
    fun clear(){
        sharedPreferences.edit().clear().apply()//this will clear whole shared preferences data
    }
}