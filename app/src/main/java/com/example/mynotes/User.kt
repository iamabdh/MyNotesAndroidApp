package com.example.mynotes

class User {
    var id : Int = 0
    var text : String = ""
    var dateTime : String = ""
    var time : String = ""


    constructor(text: String, dateTime : String, time : String){
        this.text = text
        this.dateTime = dateTime
        this.time = time
    }

    constructor(){}
}

