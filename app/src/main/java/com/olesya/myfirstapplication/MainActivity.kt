package com.olesya.myfirstapplication

import android.media.Image
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import android.text.TextUtils.indexOf
import android.text.TextUtils.lastIndexOf
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import java.io.RandomAccessFile
import kotlin.random.Random

private const val TAG = "MainActivity"
private const val My_Own_Log_TAG = "MyOwnLog"
private const val VALUE = "Value" //ключ для Bundle

class MainActivity<listOf> : AppCompatActivity() {

    private lateinit var helloTextView: TextView //раняя инциализ для всего класса
    private lateinit var randomizeButton: Button
    //private var stihstring: String = stih[0]
    private var stih: List<String> = listOf<String>(
        "Ты видел деву на скале",
        "В одежде белой над волнами",
        "Когда, бушуя в бурной мгле,",
        "Играло море с берегами,",
        "Когда луч молний озарял",
        "Ее всечасно блеском алым",
        "И ветер бился и летал",
        "С ее летучим покрывалом?",
        "Прекрасно море в бурной мгле",
        "И небо в блесках без лазури;",
        "Но верь мне: дева на скале",
        "Прекрасней волн, небес и бури."
    )

    private var stihstring: String = stih[0]

    override fun onCreate(savedInstanceState: Bundle?) { //?-может быть нулл
        //статич данные, указ. какая верстка
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helloTextView = findViewById(R.id.hello_textview) //присвоили значение, ранее иниц перем
        randomizeButton = findViewById(R.id.randomize_button)

        Log.d(My_Own_Log_TAG, stihstring)
        stihstring = stih[((stih.indexOf(stihstring))+1)%12]


        if (savedInstanceState != null) //проверяем не пустой ли Bundle
            helloTextView.text =
                savedInstanceState.getString(VALUE) //если не пустой подставляем наш ключ, вытягиваем знач
        else
            randomize()


        val threebutton: Button = findViewById(R.id.three_button)
        val onebutton: Button = findViewById(R.id.one_button)
        val twoButton: Button = findViewById(R.id.two_button)
        val onetext: TextView = findViewById(R.id.one_TextView)
        val twotext: TextView = findViewById(R.id.two_TextView)
        val threetext: TextView = findViewById(R.id.three_TextView)


//        слушатель клика
        randomizeButton.setOnClickListener {
            randomize()
        }

        threebutton.setOnClickListener {
            threetext.text = "Anna"
        }

        onebutton.setOnClickListener {
            onetext.text = "Ksenya"
        }
        twoButton.setOnClickListener {
            twotext.text = "Victorya"
        }
    }

    fun randomize() {
        val randomValue = Random.nextInt(0, 101)
        helloTextView.text = randomValue.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.d(My_Own_Log_TAG, stihstring)
        stihstring = stih[((stih.indexOf(stihstring))+1)%12]
    }

    override fun onResume() {
        super.onResume()
        Log.d(My_Own_Log_TAG, stihstring)
        stihstring = stih[((stih.indexOf(stihstring))+1)%12]

    }

    override fun onPause() {
        super.onPause()
        Log.d(My_Own_Log_TAG, stihstring)
        stihstring = stih[((stih.indexOf(stihstring))+1)%12]
    }

    override fun onStop() {
        super.onStop()
        Log.d(My_Own_Log_TAG, stihstring)
        stihstring = stih[((stih.indexOf(stihstring))+1)%12]
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(My_Own_Log_TAG, stihstring)
        stihstring = stih[((stih.indexOf(stihstring))+1)%12]
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(My_Own_Log_TAG, stihstring)
        stihstring = stih[(((stih.indexOf(stihstring))+1)%12)]
    }

    //функция идет после onPause do onStop
    override fun onSaveInstanceState(outState: Bundle) {//Bundle - использ для хранения и передачи данных
        super.onSaveInstanceState(outState)//переопредел и положим знач из OnCreat
        outState.putString(
            VALUE,
            helloTextView.text.toString()
        )//передали значение, они вернуться в onCreate
    }

}