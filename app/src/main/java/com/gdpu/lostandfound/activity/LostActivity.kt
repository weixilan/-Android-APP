package com.gdpu.lostandfound.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.adapter.LostAdapter
import com.gdpu.lostandfound.pojo.Lost

class LostActivity : AppCompatActivity() {
    private val lostlist=ArrayList<Lost>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lost)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initlost()
        val rv=findViewById<RecyclerView>(R.id.recyclerView_Lost)
        val layoutManager=LinearLayoutManager(this)
        rv.layoutManager=layoutManager
        val adapter=LostAdapter(lostlist)
        rv.adapter=adapter
    }

    private fun initlost(){
        repeat(2){
            lostlist.add(Lost("1",R.drawable.iconmonstr_mail_thin_240))
            lostlist.add(Lost("2",R.drawable.iconmonstr_mail_thin_240))
            lostlist.add(Lost("3",R.drawable.iconmonstr_mail_thin_240))
            lostlist.add(Lost("4",R.drawable.iconmonstr_mail_thin_240))
            lostlist.add(Lost("5",R.drawable.iconmonstr_mail_thin_240))
            lostlist.add(Lost("6",R.drawable.iconmonstr_mail_thin_240))
            lostlist.add(Lost("7",R.drawable.iconmonstr_mail_thin_240))
        }
    }
}