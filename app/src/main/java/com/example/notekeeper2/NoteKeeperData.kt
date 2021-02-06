package com.example.notekeeper2

data class CourseInfo (val courseid: String, val title:String) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo (var course: CourseInfo, var title: String, var text: String) {
}