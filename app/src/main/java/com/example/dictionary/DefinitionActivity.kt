package com.example.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_definition.*

class DefinitionActivity : AppCompatActivity() {
    private val key = "Word_Definition"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definition)

        text_view.text = intent.getStringExtra(key)
        img_view.setOnClickListener { finish() }
    }
}