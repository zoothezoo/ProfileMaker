package jp.co.cyberagent.dojo2019.WebActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import jp.co.cyberagent.dojo2019.R

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val str = intent.getStringExtra("str")
        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl(str)
        webView.webViewClient = WebViewClient()

        webView.getSettings().setJavaScriptEnabled(true);

    }
}
