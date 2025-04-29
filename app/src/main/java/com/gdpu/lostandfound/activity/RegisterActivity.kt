package com.gdpu.lostandfound.activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
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
import com.gdpu.lostandfound.db.LAFDatabaseHelper

class RegisterActivity : AppCompatActivity() {
    private lateinit var dbHelper : LAFDatabaseHelper
    private lateinit var username : String
    private lateinit var password : String
    private lateinit var phone : String
    private lateinit var password2 : String
    private lateinit var gender : RadioGroup
    private lateinit var sex : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //获取数据库连接
        dbHelper = LAFDatabaseHelper(this,"laf.db",6)

        //注册操作
        val button_register: Button =findViewById(R.id.register)
        button_register.setOnClickListener {
            //检查输入框内信息
            if(checkUser()){
                //添加用户信息
                addUser()
                //注册成功后跳转页面
                val intent= Intent(this,LoginActivity::class.java)
                Toast.makeText(this,"注册成功，即将返回登录界面",Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }
    }

    //检查用户是否注册过
    private fun checkUserName() : Boolean{
        val username : String =findViewById<TextView>(R.id.username).text.toString()

        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("select * from User where user_name=?", arrayOf(username))
        if(cursor.count>0){
            return false
            cursor.close()
        }
        cursor.close()
        return true
    }

    //检查输入框内信息
    private fun checkUser() : Boolean{
        username =findViewById<TextView>(R.id.username).text.toString()
        password =findViewById<TextView>(R.id.password).text.toString()
        password2 =findViewById<TextView>(R.id.password2).text.toString()
        phone =findViewById<TextView>(R.id.userphone).text.toString()
        gender =findViewById(R.id.gender)
        sex =findViewById<RadioButton>(gender.checkedRadioButtonId).text.toString()

        //判空
        if(username.isEmpty() || password.isEmpty() || password2.isEmpty() || phone.isEmpty()){
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show()
            return false
        }
        //判断两次密码是否输入一致
        else if(password!=password2){
            Toast.makeText(this,"两次密码输入不一致",Toast.LENGTH_SHORT).show()
            return false
        }
        //判断用户名是否注册过
        else if(!checkUserName()){
            Toast.makeText(this,"用户名不能重复",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    //添加用户
    private fun addUser(){
        //获取输入框内信息
        username =findViewById<TextView>(R.id.username).text.toString()
        password =findViewById<TextView>(R.id.password).text.toString()
        phone =findViewById<TextView>(R.id.userphone).text.toString()
        gender =findViewById(R.id.gender)
        sex =findViewById<RadioButton>(gender.checkedRadioButtonId).text.toString()

        //数据库添加操作
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("user_name",username)
            put("user_pwd",password)
            put("user_phone",phone)
            put("user_sex",sex)
        }
        val newRowId = db.insert("User",null,values)
    }
}