package com.gdpu.lostandfound.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.adapter.OldPubAdapter
import com.gdpu.lostandfound.pojo.OldPub

class OldPubActivity : AppCompatActivity() {
    private val oldpublist=ArrayList<OldPub>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_old_pub)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initoldpub()
        val rv=findViewById<RecyclerView>(R.id.recyclerView_Old)
        val layoutManager= LinearLayoutManager(this)
        rv.layoutManager=layoutManager
        val adapter= OldPubAdapter(oldpublist)
        rv.adapter=adapter
    }

    private fun initoldpub(){
        repeat(2){
            oldpublist.add(OldPub("1",R.drawable.iconmonstr_mail_thin_240))
            oldpublist.add(OldPub("2",R.drawable.iconmonstr_mail_thin_240))
            oldpublist.add(OldPub("3",R.drawable.iconmonstr_mail_thin_240))
            oldpublist.add(OldPub("4",R.drawable.iconmonstr_mail_thin_240))
            oldpublist.add(OldPub("5",R.drawable.iconmonstr_mail_thin_240))
            oldpublist.add(OldPub("6",R.drawable.iconmonstr_mail_thin_240))
            oldpublist.add(OldPub("7",R.drawable.iconmonstr_mail_thin_240))
        }
    }
}