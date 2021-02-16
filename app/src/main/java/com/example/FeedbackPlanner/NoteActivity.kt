package global.upshot.FeedbackPlanner

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class NoteActivity : AppCompatActivity() {
    val spinnerCourses by lazy<Spinner>{findViewById(R.id.spinnerCourses) } ;
    val noteText  by lazy<TextView>{ findViewById(R.id.textNoteText)}
    val noteTitle  by lazy<TextView>{ findViewById(R.id.textNoteTitle)}

    private var notePosition = POSITION_NOT_SET


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())

        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        this.spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(EXTRA_NOTE_POSITION, POSITION_NOT_SET) ?:
                        intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)

        if (notePosition != POSITION_NOT_SET){
            displayNote()
        }else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
        }
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        this.noteTitle.setText(note.title)
        this.noteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        this.spinnerCourses.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex){
            val menuitem = menu?.findItem(R.id.action_next)
            if (menuitem != null){
                menuitem.icon = getDrawable(R.drawable.ic_block_white_24)
                menuitem.isEnabled = false
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        this.displayNote()
        invalidateOptionsMenu()
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = this.noteTitle?.text.toString()
        note.text = this.noteText?.text.toString()
        note.course = this.spinnerCourses?.selectedItem as CourseInfo
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(EXTRA_NOTE_POSITION, notePosition)

    }
}