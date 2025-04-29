package com.gdpu.lostandfound.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
import com.gdpu.lostandfound.pojo.Lost

class PersonalInfoActivity : AppCompatActivity() {
    private lateinit var dbHelper : LAFDatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_personal_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //获取数据库连接
        dbHelper = LAFDatabaseHelper(this,"laf.db",6)
        //获取个人信息
        getInfo()

        //更新个人信息后的操作
        val button_submit=findViewById<Button>(R.id.personal_accept)
        button_submit.setOnClickListener {
            //更新个人信息
            updateInfo()
            Toast.makeText(this,"个人信息更新成功",Toast.LENGTH_SHORT).show()
            //重新转入更新后的个人信息界面
            val intent= Intent(this,PersonalInfoActivity::class.java)
            startActivity(intent)
        }
    }

    //获取个人信息
    @SuppressLint("Range")
    private fun getInfo(){
        //获取存入SharedPreferences的用户id
        val user = getSharedPreferences("User",Context.MODE_PRIVATE)
        val id = user.getString("user_id","")

        //进行数据库查询操作
        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("select * from User where user_id = ?", arrayOf(id))
        if(cursor.moveToFirst()){
            val username=cursor.getString(cursor.getColumnIndex("user_name"))
            val usersex=cursor.getString(cursor.getColumnIndex("user_sex"))
            val userphone=cursor.getString(cursor.getColumnIndex("user_phone"))
            val userpwd=cursor.getString(cursor.getColumnIndex("user_pwd"))
//          val user=cursor.getString(cursor.getColumnIndex("lost_address"))

            //在输入框显示当前的个人信息
            val name : TextView=findViewById(R.id.personal_user)
            name.text="$username"
            val phone : TextView=findViewById(R.id.personal_phone)
            phone.text="$userphone"
            val pwd : TextView=findViewById(R.id.personal_password)
            pwd.text="$userpwd"
            val man :RadioButton=findViewById(R.id.personal_man)
            val woman :RadioButton=findViewById(R.id.personal_woman)
            if(usersex=="女"){
                woman.isChecked=true
            }
            else{
                man.isChecked=true
            }
        }
        cursor.close()
    }

    //更新个人信息
    private fun updateInfo(){
        //获取存入SharedPreferences的用户id
        val user = getSharedPreferences("User",Context.MODE_PRIVATE)
        val id = user.getString("user_id","")

        ////获取输入框内信息
        val name =findViewById<TextView>(R.id.personal_user).text.toString()
        val phone =findViewById<TextView>(R.id.personal_phone).text.toString()
        val pwd =findViewById<TextView>(R.id.personal_password).text.toString()
        val gender : RadioGroup=findViewById(R.id.personal_gender)
        val sex =findViewById<RadioButton>(gender.checkedRadioButtonId).text.toString()

        //数据库的更新操作
        val db = dbHelper.writableDatabase
        val values=ContentValues().apply {
            put("user_name",name)
            put("user_pwd",pwd)
            put("user_phone",phone)
            put("user_sex",sex)
        }
        db.update("User",values,"user_id=?", arrayOf(id))
    }
}