package com.gdpu.lostandfound.activity

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.db.LAFDatabaseHelper

class DetailsActivity : AppCompatActivity() {
    private lateinit var dbHelper : LAFDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //获取数据库连接
        dbHelper = LAFDatabaseHelper(this,"laf.db",6)

        val type = intent.getStringExtra("type").toString()
        val position = intent.getStringExtra("position").toString()
//        Toast.makeText(this,"$type,$position",Toast.LENGTH_SHORT).show()

        //获取个人信息
        if(type.equals("lost")){
            getLostDetails(position)
        }
        else {
            getFoundDetails(position)
        }

    }

    //获取个人信息
    @SuppressLint("Range")
    private fun getLostDetails(position : String){
        //进行数据库查询操作
        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("select * from Lost where lost_id = ?", arrayOf(position))
        if(cursor.moveToFirst()){
            val title=cursor.getString(cursor.getColumnIndex("lost_name"))
            val details=cursor.getString(cursor.getColumnIndex("lost_details"))
            val datatime=cursor.getString(cursor.getColumnIndex("lost_time"))
            val address=cursor.getString(cursor.getColumnIndex("lost_address"))
            val phone=cursor.getString(cursor.getColumnIndex("lost_phone"))

            //在输入框显示当前的个人信息
            val detail_title : TextView =findViewById(R.id.detail_title)
            detail_title.text="$title"
            val detail_details : TextView =findViewById(R.id.detail_details)
            detail_details.text="$details"
            val detail_data : TextView =findViewById(R.id.detail_data)
            detail_data.text="$datatime"
            val detail_address : TextView =findViewById(R.id.detail_address)
            detail_address.text="$address"
            val detail_phone : TextView =findViewById(R.id.detail_phone)
            detail_phone.text="$phone"
        }
        cursor.close()
    }

    @SuppressLint("Range")
    private fun getFoundDetails(position : String){
        //进行数据库查询操作
        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("select * from Found where find_id = ?", arrayOf(position))
        if(cursor.moveToFirst()){
            val title=cursor.getString(cursor.getColumnIndex("find_name"))
            val details=cursor.getString(cursor.getColumnIndex("find_details"))
            val datatime=cursor.getString(cursor.getColumnIndex("find_time"))
            val address=cursor.getString(cursor.getColumnIndex("find_address"))
            val phone=cursor.getString(cursor.getColumnIndex("find_phone"))

            //在输入框显示当前的个人信息
            val detail_title : TextView =findViewById(R.id.detail_title)
            detail_title.text="$title"
            val detail_details : TextView =findViewById(R.id.detail_details)
            detail_details.text="$details"
            val detail_data : TextView =findViewById(R.id.detail_data)
            detail_data.text="$datatime"
            val detail_address : TextView =findViewById(R.id.detail_address)
            detail_address.text="$address"
            val detail_phone : TextView =findViewById(R.id.detail_phone)
            detail_phone.text="$phone"
        }
        cursor.close()
    }
}