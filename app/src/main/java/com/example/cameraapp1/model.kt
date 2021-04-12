package com.example.cameraapp1

data class model(
    val user:User

){
    constructor():this(User("","",0,""))
}
data class User(  var firstName: String,
                  var lastName: String,
                  var employeeID: Int,
                  var dateTime: String){
 constructor():this("","",0,"")
}

