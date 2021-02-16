package global.upshot.FeedbackPlanner

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses(){
        val course0: CourseInfo = CourseInfo("sa",  "Sheila Aguinaldo")
        courses.set(course0.courseid, course0)

        val course1: CourseInfo = CourseInfo("cdr",  "Cristina Del Rosario")
        courses.set(course1.courseid, course1)

        val course2: CourseInfo = CourseInfo(title="Mikha Gallego", courseid = "mg" )
        courses.set(course2.courseid, course2)

        val course3: CourseInfo = CourseInfo("rvc",  "Rolan Veron Cruz")
        courses.set(course3.courseid, course3)

    }
    private fun initializeNotes(){
        var course = courses["sa"]!!
        var note = NoteInfo(course, "improve peer communication",
            "Whatever other details")
        notes.add(note)
        note = NoteInfo(course, "improve tardiness",
            "Whatever  other details")
        notes.add(note)

        course = courses["cdr"]!!
        note = NoteInfo(course, "good job last Monday",
            "Whatever other details")
        notes.add(note)
        note = NoteInfo(course, "good job Tuesday",
            "Whatever other details")
        notes.add(note)

        course = courses["mg"]!!
        note = NoteInfo(course, " absence problem",
            "Whatever other details")
        notes.add(note)
        note = NoteInfo(course, "conflict resolution",
            "Whatever other details")
        notes.add(note)

        course = courses["rvc"]!!
        note = NoteInfo(course, " tardiness problem",
            "Whatever other details")
        notes.add(note)
        note = NoteInfo(course, "attitude problem",
            "Whatever other details")
        notes.add(note)
    }
}