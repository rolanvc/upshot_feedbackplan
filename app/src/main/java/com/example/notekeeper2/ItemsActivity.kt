package com.example.notekeeper2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val listItems by lazy{ findViewById<RecyclerView>(R.id.listItems)}
    private val drawerLayout by lazy{  findViewById<DrawerLayout>(R.id.drawer_layout)}
    private val noteLayoutManager by lazy { LinearLayoutManager(this)}
    private val noteRecyclerAdapter by lazy {NoteRecyclerAdapter(this, DataManager.notes)}
    private val navView: NavigationView by lazy { findViewById(R.id.nav_view) }
    private val courseLayoutManager by lazy { GridLayoutManager(this,2 ) }
    private val courseRecyclerAdapter by lazy {CourseRecyclerAdapter(this, DataManager.courses.values.toList())}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val activityIntent = Intent(this, NoteActivity::class.java)
            startActivity(activityIntent)
        }

        displayNotes()

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav_draw,
        R.string.close_nav_draw)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        navView.setNavigationItemSelectedListener(this)
    }

    private fun displayNotes() {
        this.listItems.layoutManager = noteLayoutManager
        this.listItems.adapter = noteRecyclerAdapter
       navView.menu.findItem(R.id.nav_notes).isChecked = true
    }

    private fun displayCourses() {
        this.listItems.layoutManager = courseLayoutManager
        this.listItems.adapter = courseRecyclerAdapter
        navView.menu.findItem(R.id.nav_notes).isChecked = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

/*
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
*/

    override fun onResume(){
        super.onResume()
        this.listItems.adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_courses ->{
                displayCourses()
            }
            R.id.nav_notes -> {
                displayNotes()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleSelection(message: String) {
        Snackbar.make(listItems, message, Snackbar.LENGTH_LONG).show()

    }
}