package com.example.kuka0.intranet.Database

class Course {
    var course_name: String=""
    var course_description: String=""
    var course_year: String=""
    var course_credits: String=""
    var uid: String=""
    var teacher_id: String=""

    constructor(name: String, description: String, year: String, credits: String, teacher_id: String) {
        this.course_name = name
        this.course_description = description
        this.course_year = year
        this.course_credits = credits
        this.teacher_id = teacher_id
    }
    constructor(name:String,description: String,year:String,credits: String){
        this.course_name =name
        this.course_description =description
        this.course_year =year
        this.course_credits =credits
    }
    constructor(name:String,year:String){
        this.course_name =name
        this.course_year =year
    }
}

