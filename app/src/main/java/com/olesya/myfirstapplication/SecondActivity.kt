package com.olesya.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


//private const val KEY = "HELLO_KEY" //ключ для intent

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val hello: String? = intent.extras?.getString(KEY) //вызываем extras ищем ключ,явно указ тип данные?

        Toast.makeText(this, hello, Toast.LENGTH_LONG).show() //данные обяз string or char for toast
    }
}