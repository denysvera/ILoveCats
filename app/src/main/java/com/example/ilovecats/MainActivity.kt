package com.example.ilovecats

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.ilovecats.Helpers.OverlayView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var loadingProgress: OverlayView
    lateinit var loadingText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        loadingProgress = findViewById(R.id.loadingProgress)
        loadingText = findViewById(R.id.loadingText)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun showLoadingDialog(message: String?){
        loadingProgress.visibility = View.VISIBLE
        loadingText.text = message
    }
    fun closeLoadingDialog(){
        loadingProgress.visibility = View.GONE
        loadingText.text = ""
    }

    fun showErrorDialog(title:String,  msg: String?){

        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(msg)

        builder.setPositiveButton("Ok") { dialog, which ->

        }
        builder.show()
    }

}