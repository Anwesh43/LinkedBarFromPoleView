package com.anwesh.uiprojects.linkedbarfrompoleview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.barfrompoleview.BarFromPoleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarFromPoleView.create(this)
    }
}
