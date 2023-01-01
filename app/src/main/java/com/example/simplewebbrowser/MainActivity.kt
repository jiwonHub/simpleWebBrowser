package com.example.simplewebbrowser

import android.annotation.SuppressLint
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val addressBar: EditText by lazy{
        findViewById(R.id.addressBar)
    }
    private val webView: WebView by lazy{
        findViewById(R.id.webView)
    }
    private val goHomeButton : Button by lazy{
        findViewById(R.id.goHomeButton)
    }
    private val goBackButton: Button by lazy{
        findViewById(R.id.goBackButton)
    }
    private val goForwardButton: Button by lazy{
        findViewById(R.id.goForwardButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindViews()
    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }
    }

    @SuppressLint("SetJavaScriptEnabled") // 보안상의 이슈 때문에 자바스크립트 기능을 제공하지 않았음.
    private fun initViews(){
        webView.webViewClient = WebViewClient() // 웹을 외부에서 열지않고 앱 내에서 바로 열기
        webView.settings.javaScriptEnabled = true // 웹에서 제공하는 자바스크립트 기능을 수행
        webView.loadUrl(DEFAULT_URL) // 구글을 웹 기본 창으로 설정
    }

    private fun bindViews(){
        goHomeButton.setOnClickListener {
            webView.loadUrl(DEFAULT_URL) // 메인 화면으로 가기
        }

        addressBar.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                webView.loadUrl(textView.text.toString())
            }
            return@setOnEditorActionListener false
        }


        goBackButton.setOnClickListener{
            webView.goBack() // 뒤로가기
        }

        goForwardButton.setOnClickListener {
            webView.goForward() // 앞으로가기
        }
    }

    companion object{
        private const val DEFAULT_URL = "http://www.google.com"
    }
}