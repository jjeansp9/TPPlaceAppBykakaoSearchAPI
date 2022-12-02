package com.jspstudio.tpplaceappbykakaosearchapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jspstudio.tpplaceappbykakaosearchapi.R
import com.jspstudio.tpplaceappbykakaosearchapi.databinding.ActivityEmailLoginBinding

class EmailLoginActivity : AppCompatActivity() {

    val binding: ActivityEmailLoginBinding by lazy { ActivityEmailLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_email_login)
        setContentView(binding.root)

        // 툴바에 업버튼 만들기
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

    }
}







































