package com.example.kuka0.intranet.Database

class Marks {
    var mark_value:String=""
    var teacher_id:String=""
    var course_id:String=""
    var student_id:String=""
    var uid:String=""
    var full_name:String=""
    constructor()

    constructor(value:String,teacher_id:String,course_id:String,student_id:String,id:String,name:String){
        this.mark_value = value
        this.teacher_id = teacher_id
        this.course_id = course_id
        this.student_id = student_id
        this.uid = id
        this.full_name = name
    }
    constructor(value:String,course_idb:String){
        this.mark_value = value
        this.course_id = course_id
    }
}