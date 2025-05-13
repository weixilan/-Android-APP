package com.gdpu.lostandfound.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.KotlinStudyApplication
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.activity.LoginActivity
import com.gdpu.lostandfound.db.LAFDatabaseHelper
import com.gdpu.lostandfound.home.AddActivity
import com.gdpu.lostandfound.pojo.Lost

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment(val title: String) : Fragment() {
    private lateinit var dbHelper : LAFDatabaseHelper

    private lateinit var name : String
    private lateinit var details : String
    private lateinit var time : String
    private lateinit var address : String
    private lateinit var phone : String
    private lateinit var image : String

    private lateinit var types : RadioGroup
    private lateinit var type : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        dbHelper = LAFDatabaseHelper(requireContext(),"laf.db",6)

        val button_submit : Button = view.findViewById(R.id.add_accept)
        button_submit.setOnClickListener {
            if(checkInput(view)){
                addItem(view)
                val intent= Intent(requireContext(), AddActivity::class.java)
                Toast.makeText(requireContext(),"发布成功", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }

        return view
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//    }

    private fun checkInput(view : View) : Boolean{
        name = view.findViewById<TextView>(R.id.add_title).text.toString()
        details = view.findViewById<TextView>(R.id.add_details).text.toString()
        phone = view.findViewById<TextView>(R.id.add_phone).text.toString()

        if(name.isEmpty() || details.isEmpty() || phone.isEmpty()){
            Toast.makeText(requireContext(),"输入不能为空",Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    private fun addItem(view : View) {
        name = view.findViewById<TextView>(R.id.add_title).text.toString()
        details = view.findViewById<TextView>(R.id.add_details).text.toString()
        time = view.findViewById<TextView>(R.id.add_data).text.toString()
        address = view.findViewById<TextView>(R.id.add_address).text.toString()
        phone = view.findViewById<TextView>(R.id.add_phone).text.toString()
//        image =view.findViewById<TextView>(R.id.add_phone).text.toString()

        types = view.findViewById(R.id.add_type)
        type = view.findViewById<RadioButton>(types.checkedRadioButtonId).text.toString()
        Toast.makeText(requireContext(),"$type",Toast.LENGTH_SHORT).show()

        val user = requireContext().getSharedPreferences("User",Context.MODE_PRIVATE)
        val id =user.getString("user_id" ,"")
        Toast.makeText(requireContext(),"$id",Toast.LENGTH_SHORT).show()

        val db = dbHelper.writableDatabase

        if (type == "失物") {
            val values = ContentValues().apply {
                put("lost_name", name)
                put("lost_details", details)
                put("lost_time", time)
                put("lost_address", address)
                put("lost_phone", phone)
//                put("lost_image",image)
                put("user_id",id)
            }
            val newRowId = db.insert("Lost", null, values)
        }

        else {
            val values = ContentValues().apply {
                put("find_name", name)
                put("find_details", details)
                put("find_time", time)
                put("find_address", address)
                put("find_phone", phone)
//                put("find_image",image)
                put("user_id",id)
            }
            val newRowId = db.insert("Found", null, values)


        }
    }
}