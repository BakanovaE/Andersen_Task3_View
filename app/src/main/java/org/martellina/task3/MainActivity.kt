package org.martellina.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchFlagsActivity(view: View) {
        val intent = Intent(this, FlagsActivity::class.java)
        startActivity(intent)
    }

    fun launchPicturesActivity(view: View) {
        val intent = Intent(this, PicturesActivity::class.java)
        startActivity(intent)
    }
}