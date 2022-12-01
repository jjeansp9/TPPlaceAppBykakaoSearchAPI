package com.jspstudio.tpplaceappbykakaosearchapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jspstudio.tpplaceappbykakaosearchapi.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    val binding: ActivitySignupBinding by lazy { ActivitySignupBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_signup)
        setContentView(binding.root)
    }
}