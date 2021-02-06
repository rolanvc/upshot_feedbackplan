package com.example.notekeeper2

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses(){
        val course0: CourseInfo = CourseInfo("android_intents",  "Android Programming with Intents")
        courses.set(course0.courseid, course0)

        val course1: CourseInfo = CourseInfo("android_async",  "Android Async Programming and Services")
        courses.set(course1.courseid, course1)

        val course2: CourseInfo = CourseInfo(title="Java Fundamentals: The Java Language", courseid = "java_lang" )
        courses.set(course2.courseid, course2)

        val course3: CourseInfo = CourseInfo("java_core",  "Java Fundamentals: The Core Platform")
        courses.set(course3.courseid, course3)

    }
    private fun initializeNotes(){
        var course = courses["android_intents"]!!
        var note = NoteInfo(course, "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(course, "Delegating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)

        course = courses["android_async"]!!
        note = NoteInfo(course, "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?")
        notes.add(note)
        note = NoteInfo(course, "Long running operations",
            "Foreground Services can be tied to a notification icon")
        notes.add(note)

        course = courses["java_lang"]!!
        note = NoteInfo(course, "Parameters",
            "Leverage variable-length parameter lists")
        notes.add(note)
        note = NoteInfo(course, "Anonymous classes",
            "Anonymous classes simplify implementing one-use types")
        notes.add(note)

        course = courses["java_core"]!!
        note = NoteInfo(course, "Compiler options",
            "The -jar option isn't compatible with with the -cp option")
        notes.add(note)
        note = NoteInfo(course, "Serialization",
            "Remember to include SerialVersionUID to assure version compatibility")
        notes.add(note)
    }
}