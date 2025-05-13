package com.gdpu.lostandfound.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.adapter.OldPubAdapter
import com.gdpu.lostandfound.db.LAFDatabaseHelper
import com.gdpu.lostandfound.pojo.OldPub

class OldPubActivity : AppCompatActivity() {
    private val oldpublist=ArrayList<OldPub>()
    private lateinit var dbHelper : LAFDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_old_pub)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //获取数据库连接
        dbHelper = LAFDatabaseHelper(this,"laf.db",6)

        initoldpub()
        val rv=findViewById<RecyclerView>(R.id.recyclerView_Old)
        val layoutManager= LinearLayoutManager(this)
        rv.layoutManager=layoutManager
        val adapter= OldPubAdapter(oldpublist)
        rv.adapter=adapter
    }

    @SuppressLint("Range")
    private fun initoldpub(){
        //获取存入SharedPreferences的用户id
        val user = getSharedPreferences("User", Context.MODE_PRIVATE)
        val id = user.getString("user_id","")

        //查找指定账号下发布过的失物信息与招领信息
        val db = dbHelper.writableDatabase
        var cursor = db.rawQuery("select * from Lost where user_id = ?", arrayOf(id))
//        var cursor = db.query("Lost",null,null,null,null,null,null)
        Toast.makeText(this,"${cursor.count}", Toast.LENGTH_SHORT).show()
        if(cursor.moveToFirst()){
            do{
                val name=cursor.getString(cursor.getColumnIndex("lost_name"))
                val time=cursor.getString(cursor.getColumnIndex("lost_time"))
                val address=cursor.getString(cursor.getColumnIndex("lost_address"))
//                Toast.makeText(requireContext(),"$name",Toast.LENGTH_SHORT).show()
                oldpublist.add(OldPub("标题：$name", "时间：$time","地址：$address",R.drawable.iconmonstr_mail_thin_240))
            }while (cursor.moveToNext())
        }
        cursor.close()

        cursor = db.rawQuery("select * from Found where user_id = ?", arrayOf(id))
//        cursor = db.query("Found",null,null,null,null,null,null)
        Toast.makeText(this,"${cursor.count}", Toast.LENGTH_SHORT).show()
        if(cursor.moveToFirst()){
            do{
                val name=cursor.getString(cursor.getColumnIndex("find_name"))
                val time=cursor.getString(cursor.getColumnIndex("find_time"))
                val address=cursor.getString(cursor.getColumnIndex("find_address"))
                oldpublist.add(OldPub("标题：$name", "时间：$time","地址：$address",R.drawable.iconmonstr_mail_thin_240))
            }while (cursor.moveToNext())
        }
//        repeat(2){
//            oldpublist.add(OldPub("1",R.drawable.iconmonstr_mail_thin_240))
//            oldpublist.add(OldPub("2",R.drawable.iconmonstr_mail_thin_240))
//            oldpublist.add(OldPub("3",R.drawable.iconmonstr_mail_thin_240))
//            oldpublist.add(OldPub("4",R.drawable.iconmonstr_mail_thin_240))
//            oldpublist.add(OldPub("5",R.drawable.iconmonstr_mail_thin_240))
//            oldpublist.add(OldPub("6",R.drawable.iconmonstr_mail_thin_240))
//            oldpublist.add(OldPub("7",R.drawable.iconmonstr_mail_thin_240))
//        }
    }
}