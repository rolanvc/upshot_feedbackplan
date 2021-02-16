package global.upshot.FeedbackPlanner

data class CourseInfo (val courseid: String, val title:String, var done: Boolean=false) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo (var course: CourseInfo?=null, var title: String?= null, var text: String?=null) {
}