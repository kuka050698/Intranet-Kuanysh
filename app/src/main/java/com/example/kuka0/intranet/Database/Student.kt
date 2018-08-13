package com.example.kuka0.intranet.Database

class Student {
    var name:String=""
    var age:String=""
    var uid:String=""
    var year:String=""


    constructor()
    constructor(name:String,age:String,year:String){
        this.name=name
        this.age = age
        this.year = year
    }
    constructor(name:String,age:String){
        this.name=name
        this.age = age
    }

}