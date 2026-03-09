 package com.v77.player

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var editTextVideoId: EditText
    private lateinit var buttonLoad: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        editTextVideoId = findViewById(R.id.editTextVideoId)
        buttonLoad = findViewById(R.id.buttonLoad)

        setupWebView()

        buttonLoad.setOnClickListener {
            val videoId = editTextVideoId.text.toString().trim()
            if (videoId.isNotEmpty()) {
                loadYouTubeVideo(videoId)
            } else {
                Toast.makeText(this, "Masukkan ID Video!", Toast.LENGTH_SHORT).show()
            }
        }

        // Video default: Never Gonna Give You Up
        loadYouTubeVideo("dQw4w9WgXcQ")
        editTextVideoId.setText("dQw4w9WgXcQ")
    }

    private fun setupWebView() {
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.mediaPlaybackRequiresUserGesture = false
        webSettings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
    }

    private fun loadYouTubeVideo(videoId: String) {
        val frameVideo = """
            <html>
            <body style="margin:0;padding:0;background:black;">
                <iframe 
                    class="youtube-player" 
                    type="text/html" 
                    width="100%" 
                    height="100%" 
                    src="https://www.youtube.com/embed/$videoId?autoplay=1&playsinline=1&rel=0"
                    allowfullscreen 
                    frameborder=0
                    style="border:0;">
                </iframe>
            </body>
            </html>
        """.trimIndent()

        webView.loadData(frameVideo, "text/html", "utf-8")
    }
}