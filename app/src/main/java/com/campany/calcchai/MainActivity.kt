package com.campany.calcchai

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editUrl = findViewById<EditText>(R.id.editUrl)
        val buttonOpen = findViewById<Button>(R.id.buttonOpen)
        val textResult = findViewById<TextView>(R.id.textResult)

        buttonOpen.setOnClickListener {

            val enteredUrl = editUrl.text.toString().trim()

            if (enteredUrl.isEmpty()) {
                editUrl.error = "Введите адрес сайта"
                return@setOnClickListener
            }

            val url = addHttps(enteredUrl)

            textResult.text = "Открываем: $url"

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "Не найдено приложение для открытия ссылки",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun addHttps(url: String): String {
        return if (url.startsWith("http://") || url.startsWith("https://")) {
            url
        } else {
            "https://$url"
        }
    }
}