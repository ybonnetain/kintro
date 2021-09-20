package dev.ybonnetain.kintro.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dev.ybonnetain.kintro.android.R

class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "coucou"
    }
}
