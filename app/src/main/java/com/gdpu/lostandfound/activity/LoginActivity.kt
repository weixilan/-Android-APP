package com.gdpu.lostandfound.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.db.LAFDatabaseHelper
import com.gdpu.lostandfound.fragment.LostFragment
import com.gdpu.lostandfound.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var dbHelper : LAFDatabaseHelper
    private lateinit var username : String
    private lateinit var password : String

    private lateinit var sharedpreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //获取数据库连接
        dbHelper = LAFDatabaseHelper(this,"laf.db",6)


        val button_login: Button=findViewById(R.id.login)
        button_login.setOnClickListener {
            //判断输入框内信息是否填写合格
            if(checkInput()){
                //判断用户是否存在
                if(checkUser()){
                    //登陆成功后跳转至首页
                    val intent=Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        //跳转注册页面
        val button_register: Button=findViewById(R.id.register)
        button_register.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    //判断输入合法性
    private fun checkInput() : Boolean{
        //获取输入框内信息
        username = findViewById<TextView>(R.id.username).text.toString()
        password = findViewById<TextView>(R.id.password).text.toString()
        //判空
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,"$username,$password",Toast.LENGTH_SHORT).show()
            return false
        }
//        Toast.makeText(this,"$username,$password",Toast.LENGTH_SHORT).show()
        return true

    }

    //判断用户是否存在
    @SuppressLint("Range")
    private fun checkUser() : Boolean{
        username = findViewById<TextView>(R.id.username).text.toString()
        password = findViewById<TextView>(R.id.password).text.toString()

        //数据库查询用户
        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("select * from User where user_name=?", arrayOf(username))
        //有该用户
        if(cursor.count>0){
            val cursor1 = db.rawQuery("select * from User where user_name=? and user_pwd=?", arrayOf(username,password))
            //且密码能对上
            if(cursor1.count>0){
                //获取用户id
                var user_id : String = null.toString()
                if(cursor1.moveToFirst()){
                    do {
                        user_id=cursor1.getString(cursor1.getColumnIndex("user_id"))
                    }while (cursor1.moveToNext())
                }
                //存入SharedPreferences，方便实现app内的个人操作
                sharedpreferences = getApplication().getSharedPreferences("User", Context.MODE_PRIVATE);//"User"是存储的文件名，Context.MODE_PRIVATE表示该文件只能被创建他的应用访问
                editor = sharedpreferences.edit();
                editor.putString("user_id", user_id);//第一个参数相当于一个Key，后面取出数据时需要用到这个值来索引
                editor.commit();

                cursor.close()
                cursor1.close()
                return true
            }
            //但密码对不上
            else{
                Toast.makeText(this,"密码或用户名有错误",Toast.LENGTH_SHORT).show()
                cursor.close()
                cursor1.close()
                return false
            }
        }
        //无该用户
        Toast.makeText(this,"该用户不存在",Toast.LENGTH_SHORT).show()
        cursor.close()
        return false
    }
}