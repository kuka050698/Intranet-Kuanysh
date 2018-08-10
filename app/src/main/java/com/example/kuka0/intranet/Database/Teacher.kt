package com.example.kuka0.intranet.Database

class Teacher{
        var full_name:String=""
        var degree:String=""
        var uid:String=""

    constructor()
    constructor(name:String,degree:String){
        this.full_name = name
        this.degree = degree
    }
    constructor(name:String,degree:String,uid:String){
        this.full_name = name
        this.degree = degree
        this.uid = uid
    }
}