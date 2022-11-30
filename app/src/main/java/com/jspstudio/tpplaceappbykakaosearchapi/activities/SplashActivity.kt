package com.jspstudio.tpplaceappbykakaosearchapi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 화면을 별도의 xml로 만들어서 뷰를 생성할 필요 없음

        // 테마로 배경이미지를 보여주고 1.5초 후 넘어가게 설정
//        Handler(mainLooper).postDelayed(object : Runnable {
//            override fun run() {
//            }
//        }, 1500)

        // lamda 표기로 줄이기
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
    }
}