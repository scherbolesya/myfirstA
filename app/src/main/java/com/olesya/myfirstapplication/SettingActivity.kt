package com.olesya.myfirstapplication

import android.app.Notification
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val showButton: Button = findViewById(R.id.show_button)

        showButton.setOnClickListener {
            showSnackbar(it)
        }
    }

    fun showSnackbar(view: View){
        Snackbar.make(this, view,"Snackbar showed", Snackbar.LENGTH_INDEFINITE)
            .setAction("Action"){
                //Toast.makeText(this, "Toast!", Toast.LENGTH_SHORT).show()
                val intentSettingsButton = Intent(Settings.ACTION_ADD_ACCOUNT)
                startActivity(intentSettingsButton)



            }
            .show()
    }

}