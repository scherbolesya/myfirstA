package com.olesya.myfirstapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

private const val TAG = "MainActivity"
private const val My_Own_Log_TAG = "MyOwnLog"
private const val VALUE = "Value" //ключ для Bundle

const val KEY = "HELLO_KEY" //ключ для intent явного

private const val GET_FILE_REQUEST = 1

class MainActivity<listOf>() : AppCompatActivity() {

    private lateinit var helloTextView: TextView //раняя инциализ для всего класса
    private lateinit var randomizeButton: Button
    private lateinit var threetext: TextView
    private lateinit var onetext: TextView
    private lateinit var twotext: TextView

    private var stih: List<String> = listOf<String>(
        "Ты видел деву на скале,",
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
        threetext = findViewById(R.id.three_TextView)
        onetext = findViewById(R.id.one_TextView)
        twotext = findViewById(R.id.two_TextView)

        Log.d(My_Own_Log_TAG, stihstring)
        Log.d(My_Own_Log_TAG, "onCreate")
        stihstring = stih[((stih.indexOf(stihstring)) + 1) % 12]

        if (savedInstanceState != null) { //проверяем не пустой ли Bundle
            helloTextView.text =
                savedInstanceState.getString(VALUE) //если не пустой подставляем наш ключ, вытягиваем знач
            onetext.text = savedInstanceState.getString("key1")
            twotext.text = savedInstanceState.getString("key2")
            threetext.text = savedInstanceState.getString("key3")
        } else
            randomize()


        val threebutton: Button = findViewById(R.id.three_button)
        val onebutton: Button = findViewById(R.id.one_button)
        val twoButton: Button = findViewById(R.id.two_button)

        val nextButton: Button = findViewById(R.id.next_button)//иниц кнопку


        //для файла
        val fileButton : Button = findViewById(R.id.file_Button)
        //создали intent с действием GET_CONTENT
        val intentFileButton = Intent(Intent.ACTION_GET_CONTENT)//отыщи все актииви с помощью кот можно брать content//создали интент
        intentFileButton.type = "file/*" //с типом файл  // чтобы не открывались галерии
        //создаем свой результат//зарегистр для результата активности(обьект)
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
               if (result.resultCode == RESULT_OK) //если результат ок
                    result.data?.data.toString()//путь к файлу //выдать нам  файл//далее нужно использовать
               else
                   Toast.makeText(this, "Ничего не выбрано", Toast.LENGTH_SHORT).show()  //просто вышли ничего не выбрали
           }
        fileButton.setOnClickListener {
            resultLauncher.launch(intentFileButton)//запускаем наш интент  // для файла
        }
        //для файла конец


        val settingsButton : Button = findViewById(R.id.settings_Button)
        val intentSettingsButton = Intent(Settings.ACTION_ADD_ACCOUNT)//открываем настройки
        //* intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK //ставим флаг, чтобы открывались настройки в новом окне
        settingsButton.setOnClickListener {
            startActivity(intentSettingsButton)
        }

        val sharebutton: Button = findViewById(R.id.share_button) //share кнопка поделиться
        val intentt = Intent(Intent.ACTION_SEND)//есть  интент кот хочет какте то данные
        intentt.type = "text/plain"             //с типом  данных текст
        intentt.putExtra(Intent.EXTRA_TEXT, "Скачай приложение MyFirstAppStudy gj ссылке ")//отправлять будем extra-text
        //intentt.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name))//EXTRA_TEXT с названием нашего приложения
        //
        val chooser = Intent . createChooser (intentt, "Поделиться!!!")//будет открываться нижнее диалоговое окно/только на старой версии
        sharebutton.setOnClickListener {
            startActivity(chooser)
        }

        val youtubeButton : Button = findViewById(R.id.youtube_Button)
        val youtubelink = Uri.parse(  //открываем youtube
           "vnd.youtube://rhqJP_fxdNI") // id нужного видео после//
        val intentyoutube = Intent(Intent.ACTION_VIEW, youtubelink)//
        youtubeButton.setOnClickListener {
            startActivity(intentyoutube)
        }


        //// val intent  = Intent(Intent.ACTION_VIEW) //предложит открыть все приложения кот есть, если не указ явно
        val intent = Intent(Intent.ACTION_HEADSET_PLUG)//intent по подключению наушников
        if (intent.resolveActivity(packageManager) == null)//если приложения нет кнопка будет не активной
            nextButton.isEnabled = false
        else
            nextButton.setOnClickListener {
                startActivity(intent)
            }

        ///*  nextButton.setOnClickListener {
        ///* if (intent.resolveActivity(packageManager) != null)//спросить у packageManager есть у нас хоть одна активити кот сможет это сделать
        ///* startActivity(intent) //к  кнопке nextbutton c текстом next привязано приложение кот подключает наушники
        ///* else
        //Toast.makeText(this, "Нет такого приложения", Toast.LENGTH_SHORT).show() //если приложения нет выйдет toast
        ///* (it as Button).isEnabled = false

        //startActivityForResult(intent, GET_FILE_REQUEST)//старая функция
        ///* }

        val linkButton :  Button = findViewById(R.id.link_Button)
        val link = Uri.parse("https://odin.study")//
        val intentlinkbutton = Intent(Intent.ACTION_VIEW, link)//запускаем неявный интент кот опр-т какое прилож на устройстве откроет эту ссылку
        //найти мне прилож кот по ACTION_VIEW  сможет открыть эти ссылки
        linkButton.setOnClickListener {
            startActivity(intentlinkbutton)
        }



        val secondButton : Button = findViewById(R.id.SecondActivity_Button)
        val intentSecondActivity = Intent(this, SecondActivity::class.java)//явно указываем в какую активность переходим
        intentSecondActivity.putExtra(KEY, stih[0])//кладем знач в Extra
        //что будет происходить при нажатии на кнопку
        secondButton.setOnClickListener {
            startActivity(intentSecondActivity)
        }



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
        val randomValue = Random.nextInt(1, 4)
        helloTextView.text = randomValue.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.d(My_Own_Log_TAG, stihstring)
        Log.d(My_Own_Log_TAG, "onStart")
        stihstring = stih[((stih.indexOf(stihstring)) + 1) % 12]
    }

    override fun onResume() {
        super.onResume()
        Log.d(My_Own_Log_TAG, stihstring)
        Log.d(My_Own_Log_TAG, "onResume")
        stihstring = stih[((stih.indexOf(stihstring)) + 1) % 12]

    }

    override fun onPause() {
        super.onPause()
        Log.d(My_Own_Log_TAG, stihstring)
        Log.d(My_Own_Log_TAG, "onPause")
        stihstring = stih[((stih.indexOf(stihstring)) + 1) % 12]
    }

    override fun onStop() {
        super.onStop()
        Log.d(My_Own_Log_TAG, stihstring)
        Log.d(My_Own_Log_TAG, "onStop")
        stihstring = stih[((stih.indexOf(stihstring)) + 1) % 12]
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(My_Own_Log_TAG, stihstring)
        Log.d(My_Own_Log_TAG, "onRestart")
        stihstring = stih[((stih.indexOf(stihstring)) + 1) % 12]
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(My_Own_Log_TAG, stihstring)
        Log.d(My_Own_Log_TAG, "onDestroy")
        stihstring = stih[(((stih.indexOf(stihstring)) + 1) % 12)]
    }

    //функция идет после onPause do onStop
    override fun onSaveInstanceState(outState: Bundle) {//Bundle - использ для хранения и передачи данных
        super.onSaveInstanceState(outState)//переопредел и положим знач из OnCreat
        outState.apply {
            putString(
                VALUE,
                helloTextView.text.toString()
            )//передали значение, они вернуться в onCreate
            putString(
                "key1", onetext.text.toString()
            )
            putString("key2", twotext.text.toString())
            putString("key3", threetext.text.toString())
        }
    }
}
