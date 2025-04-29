package com.gdpu.lostandfound.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.adapter.FoundAdapter
import com.gdpu.lostandfound.adapter.LostAdapter
import com.gdpu.lostandfound.db.LAFDatabaseHelper
import com.gdpu.lostandfound.pojo.Found
import com.gdpu.lostandfound.pojo.Lost

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoundFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoundFragment : Fragment() {
    private val foundList = ArrayList<Found>()
    private lateinit var dbHelper : LAFDatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_found, container, false)
        dbHelper = LAFDatabaseHelper(requireContext(),"laf.db",6)

        initFound()
        val rv=view.findViewById<RecyclerView>(R.id.recyclerView_Found)
        val layoutManager= LinearLayoutManager(requireContext())
        rv.layoutManager=layoutManager
        val adapter= FoundAdapter(foundList)
        rv.adapter=adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    @SuppressLint("Range")
    private fun initFound() {
        val db = dbHelper.writableDatabase
        val cursor = db.query("Found",null,null,null,null,null,null)
        Toast.makeText(requireContext(),"${cursor.count}", Toast.LENGTH_SHORT).show()
        if(cursor.moveToFirst()){
            do{
                val name=cursor.getString(cursor.getColumnIndex("find_name"))
                val time=cursor.getString(cursor.getColumnIndex("find_time"))
                val address=cursor.getString(cursor.getColumnIndex("find_address"))
                foundList.add(Found("标题：$name", "时间：$time","地址：$address",R.drawable.iconmonstr_mail_thin_240))
            }while (cursor.moveToNext())
        }
//        repeat(2) {
//            foundList.add(Found("1", R.drawable.iconmonstr_mail_thin_240))
//            foundList.add(Found("2", R.drawable.iconmonstr_mail_thin_240))
//            foundList.add(Found("3", R.drawable.iconmonstr_mail_thin_240))
//            foundList.add(Found("4", R.drawable.iconmonstr_mail_thin_240))
//            foundList.add(Found("5", R.drawable.iconmonstr_mail_thin_240))
//            foundList.add(Found("6", R.drawable.iconmonstr_mail_thin_240))
//            foundList.add(Found("7", R.drawable.iconmonstr_mail_thin_240))
//        }
    }
}