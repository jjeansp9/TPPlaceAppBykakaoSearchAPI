package com.jspstudio.tpplaceappbykakaosearchapi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.jspstudio.tpplaceappbykakaosearchapi.G
import com.jspstudio.tpplaceappbykakaosearchapi.databinding.ActivityLoginBinding
import com.jspstudio.tpplaceappbykakaosearchapi.model.UserAccount
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

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

        // 카카오 SDK용 키해시 값 얻어오기
        var keyHash:String= Utility.getKeyHash(this)
        Log.i("keyHash", keyHash)

    }

    private fun kakaoLogin(){
        // Kakao Login API를 이용하여 사용자 정보 취득

        // 로그인 시도한 결과를 받았을 때 발동하는 콜백함수를 별도로 만들기
        val callback:(OAuthToken?, Throwable?)->Unit= { token, error ->
            if (error != null){
                Toast.makeText(this, "카카오 로그인실패", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "카카오 로그인성공", Toast.LENGTH_SHORT).show()

                // 사용자 정보 요청
                UserApiClient.instance.me { user, error ->
                    if (user!=null){
                        var id:String = user.id.toString()
                        var email:String = user.kakaoAccount?.email ?: "" // 혹시 이메일이 없다면, 이메일의 기본값을 ""로

                        Toast.makeText(this, "사용자 이메일 정보 : $email", Toast.LENGTH_SHORT).show()
                        G.userAccount= UserAccount(id, email)

                        // 로그인이 성공했으니 Main 화면으로 전환
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }

        // 카카오톡 로그인을 권장하지만 설치가 되어있지 않다면 카카오계정으로 로그인 시도
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        }else{
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun googleLogin(){
        // 구글 로그인 화면(액티비티)를 실행하여 결과를 받아와서 사용자정보 취득 - android google login 검색

        // 구글 로그인 옵션객체 생성 - Builder 이용
        val signInOptions: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail() // 이메일 정보를 받을 수 있는 로그인옵션
            .build()

        // 구글 로그인 화면(Activirt)가 이미 라이브러리에 만들어져 있음
        // 그러니 그 액티비티를 실행시켜주는 Intent 객체를 소환하기
        val intent:Intent= GoogleSignIn.getClient(this,  signInOptions).signInIntent

        // 로그인 결과를 받기위해 액티비티를 실행
        googleResultLauncher.launch(intent)
    }

    // 구글 로그인 화면액티비티를 실행시키고 그 결과를 되돌려받는 작업을 관리하는 객체 생성
    val googleResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), object : ActivityResultCallback<ActivityResult>{
        override fun onActivityResult(result: ActivityResult?) {
            // 로그인 결과를 가져온 인텐트 객체 소환
            val intent: Intent? = result?.data
            // 돌아온 Intent 객체에게 구글 계정정보를 빼오기
            val task:Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(intent)

            val account:GoogleSignInAccount = task.result
            var id:String= account.id.toString()
            var email:String= account.email ?: "" // 혹시 null이면 이메일 기본값 ""

            Toast.makeText(this@LoginActivity, "Google 로그인 성공 : $email", Toast.LENGTH_SHORT).show()
            G.userAccount= UserAccount(id, email)

            // Main 화면으로 전환
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()

        }
    })

    private fun naverLogin(){
        // 사용자정보를 취득하는 토큰값을 발급받아 REST API 방식으로 사용자정보 취득
    }




}

























