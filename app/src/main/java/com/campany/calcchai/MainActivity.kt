package com.campany.calcchai

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editUrl = findViewById<EditText>(R.id.editUrl)
        val buttonGo = findViewById<Button>(R.id.buttonGo)
        val buttonBack = findViewById<Button>(R.id.buttonBack)

        webView = findViewById(R.id.webView)

        webView.webViewClient = WebViewClient()

        buttonGo.setOnClickListener {

            val enteredUrl = editUrl.text.toString().trim()

            if (enteredUrl.isEmpty()) {
                editUrl.error = "Введите адрес сайта"
                return@setOnClickListener
            }

            val url = addHttps(enteredUrl)

            webView.loadUrl(url)
        }

        buttonBack.setOnClickListener {

            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                Toast.makeText(this, "Истории нет", Toast.LENGTH_SHORT).show()
                finish()
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