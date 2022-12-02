package com.jspstudio.tpplaceappbykakaosearchapi.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.jspstudio.tpplaceappbykakaosearchapi.R
import com.jspstudio.tpplaceappbykakaosearchapi.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    val binding: ActivitySignupBinding by lazy { ActivitySignupBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_signup)
        setContentView(binding.root)

        // 툴바를 액션바로 설정
        setSupportActionBar(binding.toolbar)
        // 액션바에 업버튼 설정 및 제목글씨 제거
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        binding.btnSignup.setOnClickListener{clickSignUp()}
    }

    // 업버튼 클릭할 때 자동 발동하는 콜백메소드
    override fun onSupportNavigateUp(): Boolean {
        //finish()
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    // Sign Up 버튼누르면 작동하는 메소드
    private fun clickSignUp(){
        // Firebase Firestore DB에 사용자 정보 저장하기 [ 앱과 firebase 플랫폼 연동하기 ]
        var email:String = binding.etEmail.text.toString()
        var password:String = binding.etPassword.text.toString()
        var passwordConfirm:String = binding.etPasswordConfirm.text.toString()
    
        // 원래는 정규표현식(RegExp)을 이용하여 유효성 검사함. 시간상 패스

        // 패스워드가 올바른지 확인
        if(password != passwordConfirm){
            AlertDialog.Builder(this).setMessage("비밀번호 확인에 문제가 있습니다. 다시 확인하여 입력해 주시기 바랍니다").show()
            binding.etPasswordConfirm.selectAll() // 써있는 글씨를 모두 선택상태로 하여 손쉽게 새로 입력이 가능함
            return
        }

        // Firebase Firestore에 DB에 저장하기 위해 Firestore DB 관리자객체 소환
        var db = FirebaseFirestore.getInstance()

        // 이미 가입한 적이 있는 email인지 검사
        // 필드값 중에 'email' 의 값이 EditText에 입력한 email과 같은 것이 있는지 찾아달라고 요청
        db.collection("emailUsers")
            .whereEqualTo("email", email)
            .get().addOnSuccessListener {
                // 같은 값을 가진 Document가 있다면.. 기존에 같은 email이 있다는 것임
                if (it.documents.size > 0){
                    AlertDialog.Builder(this).setMessage("중복된 이메일입니다.").show()
                    binding.etEmail.requestFocus() // selectAll() 하려면 포커스가 있어야 함
                    binding.etEmail.selectAll()
                }else{
                    // 신규 email

                    // 저장할 데이터들을 하나로 묶기위해 HashMap
                    var user: MutableMap<String, String> = mutableMapOf()
                    user.put("email", email)
                    user.put("password", password)

                    // DB안에 Collection 명은 "emailUsers" 로 지정 [ RDBMS 의 테이블 이름 같은 역할 ]
                    db.collection("emailUsers").add(user).addOnSuccessListener {
                        AlertDialog.Builder(this)
                            .setMessage("알림\n\n회원가입이 완료되었습니다.")
                            .setPositiveButton("확인", object : DialogInterface.OnClickListener{
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    finish()
                                }
                            }).show()
                    }.addOnFailureListener{
                        Toast.makeText(this, "회원가입 실패 : ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }


    }

}



































