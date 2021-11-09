package com.olesya.myfirstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


//private const val KEY = "HELLO_KEY" //ключ для intent

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val hello: String? = intent.extras?.getString(KEY) //вызываем extras ищем ключ,явно указ тип данные?, кладем в  переменную hello

        Toast.makeText(this, hello, Toast.LENGTH_LONG).show() //данные обяз string or char for toast


        val imageButton : Button = findViewById(R.id.image_button)
        val intentSettingActivity  = Intent(this, SettingActivity::class.java)

        imageButton.setOnClickListener {

            startActivity(intentSettingActivity)
        }

    }
}