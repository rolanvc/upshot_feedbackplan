package  global.upshot.FeedbackPlanner

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity() {
    var listItems: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        this.listItems= findViewById(R.id.listItems)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val activityIntent = Intent(this, NoteActivity::class.java)
            startActivity(activityIntent)

        }
        listItems?.layoutManager = LinearLayoutManager(this)
        listItems?.adapter = NoteRecyclerAdapter(this, DataManager.notes)
    }
    override fun onResume() {
        super.onResume()
        listItems?.adapter?.notifyDataSetChanged()
    }
}