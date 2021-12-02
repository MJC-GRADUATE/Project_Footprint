package kr.ac.mjc.footprint

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirstActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        var auth= FirebaseAuth.getInstance()
        moveMain(auth.currentUser)



        /*first_login = findViewById(R.id.first_login)
        first_sign_in = findViewById(R.id.first_sign_up)

        first_login.setOnClickListener {
            var intent= Intent(this,LoginActivity::class.java) // 인텐트를 이용하여 메인 액티비티로 이동
            startActivity(intent)
        }

        first_sign_in.setOnClickListener {
            var intent2 = Intent(this, SignUpActivity::class.java)
            startActivity(intent2)
        }*/

    }

    fun moveMain(user: FirebaseUser?){ // 로그인된 사용자 확인
        if(user==null){
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        var intent= Intent(this,MainActivity::class.java) // 인텐트롤 이용하여 메인 액티비티로 이동
        startActivity(intent)
        finish()

    }

}