package com.gdpu.lostandfound.activity

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gdpu.lostandfound.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //存储新的用户


        //注册成功后跳转页面
        val button_register: Button =findViewById(R.id.register)
        button_register.setOnClickListener {
            val username : String =findViewById<TextView>(R.id.username).text.toString()
            val password : String=findViewById<TextView>(R.id.password).text.toString()
            val password2 : String=findViewById<TextView>(R.id.password2).text.toString()
            val phone : String=findViewById<TextView>(R.id.userphone).text.toString()
            val gender : RadioGroup=findViewById(R.id.gender)
            val sex : String=findViewById<RadioButton>(gender.checkedRadioButtonId).text.toString()
            Toast.makeText(this,username+password+password2+phone+sex,Toast.LENGTH_SHORT).show()
//            val intent= Intent(this,LoginActivity::class.java)
//            Toast.makeText(this,"注册成功，即将返回登录界面",Toast.LENGTH_SHORT).show()
//            startActivity(intent)
        }
    }
}