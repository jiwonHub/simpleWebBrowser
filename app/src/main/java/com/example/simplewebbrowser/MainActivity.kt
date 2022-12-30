package com.example.simplewebbrowser

import android.annotation.SuppressLint
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val addressBar: EditText by lazy{
        findViewById(R.id.addressBar)
    }
    private val webView: WebView by lazy{
        findViewById(R.id.webView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindViews()
    }

    @SuppressLint("SetJavaScriptEnabled") // 보안상의 이슈 때문에 자바스크립트 기능을 제공하지 않았음.
    private fun initViews(){
        webView.webViewClient = WebViewClient() // 웹을 외부에서 열지않고 앱 내에서 바로 열기
        webView.settings.javaScriptEnabled = true // 웹에서 제공하는 자바스크립트 기능을 수행
        webView.loadUrl("http://www.google.com") // 구글을 웹 기본 창으로 설정
    }

    private fun bindViews(){
        addressBar.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                webView.loadUrl(textView.text.toString())
            }
            return@setOnEditorActionListener false
        }
    }
}