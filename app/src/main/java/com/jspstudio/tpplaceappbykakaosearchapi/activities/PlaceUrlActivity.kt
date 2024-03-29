package com.jspstudio.tpplaceappbykakaosearchapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.jspstudio.tpplaceappbykakaosearchapi.databinding.ActivityPlaceUrlBinding

class PlaceUrlActivity : AppCompatActivity() {

    val binding: ActivityPlaceUrlBinding by lazy { ActivityPlaceUrlBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // WebView를 사용하려면 아래 세줄의 코드는 꼭 입력해야함
        binding.wv.webViewClient= WebViewClient() // 현재 웹뷰안에 웹문서를 열리도록
        binding.wv.webChromeClient= WebChromeClient() // 웹페이지의 다이얼로그 같은 것들이 발동하도록
        binding.wv.settings.javaScriptEnabled= true // 웹뷰에서 JS 를 실행하도록 설정

        val placeUrl:String= intent.getStringExtra("place_url") ?: ""
        binding.wv.loadUrl(placeUrl)



    }

    override fun onBackPressed() {
        if (binding.wv.canGoBack()) binding.wv.goBack()
        else super.onBackPressed()
    }

}