package com.jspstudio.tpplaceappbykakaosearchapi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jspstudio.tpplaceappbykakaosearchapi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    val binding:ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        setContentView(binding.root)

        // 둘러보기 글씨 클릭으로 로그인 없이 Main 화면으로 이동
        binding.tvGo.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // 회원가입 버튼 클릭
        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        // 이메일 로그인 버튼
        binding.layoutEmail.setOnClickListener{startActivity(Intent(this, EmailLoginActivity::class.java))}

        // 간편로그인 버튼들
        binding.btnLoginKakao.setOnClickListener{kakaoLogin()}
        binding.btnLoginGoogle.setOnClickListener{googleLogin()}
        binding.btnLoginNaver.setOnClickListener{naverLogin()}

    }

    private fun kakaoLogin(){
        Toast.makeText(this, "kakao", Toast.LENGTH_SHORT).show()
    }

    private fun googleLogin(){
        Toast.makeText(this, "google", Toast.LENGTH_SHORT).show()
    }

    private fun naverLogin(){
        Toast.makeText(this, "naver", Toast.LENGTH_SHORT).show()
    }




}













