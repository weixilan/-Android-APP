package com.gdpu.lostandfound.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.fragment.LostFragment
import com.gdpu.lostandfound.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //判断用户是否存在

        //跳转至其他页面
        val button_login: Button=findViewById(R.id.login)
        button_login.setOnClickListener {
//            val intent=Intent(this,LostActivity::class.java)
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        val button_register: Button=findViewById(R.id.register)
        button_register.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}