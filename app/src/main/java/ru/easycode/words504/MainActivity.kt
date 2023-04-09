package ru.easycode.words504

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.easycode.words504.tts.TTSTestActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.buttonTestTTS).setOnClickListener {
            startActivity(Intent(this, TTSTestActivity::class.java))
        }
    }
}
