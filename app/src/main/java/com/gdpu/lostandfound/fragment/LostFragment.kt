package com.gdpu.lostandfound.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.adapter.LostAdapter
import com.gdpu.lostandfound.db.LAFDatabaseHelper
import com.gdpu.lostandfound.pojo.Lost

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LostFragment : Fragment() {
    private val lostList = ArrayList<Lost>()
    private lateinit var dbHelper : LAFDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_lost, container, false)
        dbHelper = LAFDatabaseHelper(requireContext(),"laf.db",6)

        initLost()
        val rv=view.findViewById<RecyclerView>(R.id.recyclerView_Lost)
        val layoutManager=LinearLayoutManager(requireContext())
        rv.layoutManager=layoutManager
        val adapter=LostAdapter(lostList)
        rv.adapter=adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    @SuppressLint("Range")
    private fun initLost() {
        val db = dbHelper.writableDatabase
        val cursor = db.query("Lost",null,null,null,null,null,null)
        Toast.makeText(requireContext(),"${cursor.count}",Toast.LENGTH_SHORT).show()
        if(cursor.moveToFirst()){
            do{
                val name=cursor.getString(cursor.getColumnIndex("lost_name"))
                val time=cursor.getString(cursor.getColumnIndex("lost_time"))
                val address=cursor.getString(cursor.getColumnIndex("lost_address"))
//                Toast.makeText(requireContext(),"$name",Toast.LENGTH_SHORT).show()
                lostList.add(Lost("标题：$name", "时间：$time","地址：$address",R.drawable.iconmonstr_mail_thin_240))
            }while (cursor.moveToNext())
        }
        cursor.close()
//        repeat(2) {
//            lostList.add(Lost("1", R.drawable.iconmonstr_mail_thin_240))
//            lostList.add(Lost("2", R.drawable.iconmonstr_mail_thin_240))
//            lostList.add(Lost("3", R.drawable.iconmonstr_mail_thin_240))
//            lostList.add(Lost("4", R.drawable.iconmonstr_mail_thin_240))
//            lostList.add(Lost("5", R.drawable.iconmonstr_mail_thin_240))
//            lostList.add(Lost("6", R.drawable.iconmonstr_mail_thin_240))
//            lostList.add(Lost("7", R.drawable.iconmonstr_mail_thin_240))
//        }
    }
}