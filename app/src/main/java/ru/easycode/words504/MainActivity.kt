package ru.easycode.words504

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.easycode.words504.recognition.TestVoiceRecognitionActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val testButton = findViewById<Button>(R.id.testButton)
        testButton.setOnClickListener {
            val intent = Intent(this, TestVoiceRecognitionActivity::class.java)
            startActivity(intent)
        }
    }
}
