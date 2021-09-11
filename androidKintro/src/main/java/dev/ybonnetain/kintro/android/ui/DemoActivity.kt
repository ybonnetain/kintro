package dev.ybonnetain.kintro.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dev.ybonnetain.kintro.Greeting
import android.widget.TextView
import dev.ybonnetain.kintro.Repository
import dev.ybonnetain.kintro.android.R


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun greet(): String {
    return Greeting().greeting()
}

class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()


        val repository = Repository()
        MainScope().launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getTodo(1)
            }.onSuccess {
                Log.d("todo", it.title)
                Log.d("todo", it.completed.toString())
                it.toggleCompleted()
                Log.d("todo", it.completed.toString())
            }.onFailure {
                Log.d("todo", it.localizedMessage)
            }

        }

    }
}
