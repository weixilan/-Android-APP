package com.gdpu.lostandfound.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.activity.LoginActivity
import com.gdpu.lostandfound.activity.OldPubActivity
import com.gdpu.lostandfound.activity.PersonalInfoActivity
import com.gdpu.lostandfound.home.AddActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_personal, container, false)

        val button_add=view.findViewById<Button>(R.id.addmsg_button)
        button_add.setOnClickListener {
            val intent= Intent(requireContext(), AddActivity::class.java)
            startActivity(intent)
        }

        val button_old=view.findViewById<Button>(R.id.oldmsg_button)
        button_old.setOnClickListener {
            val intent= Intent(requireContext(), OldPubActivity::class.java)
            startActivity(intent)
        }

        val button_personal=view.findViewById<Button>(R.id.personal_button)
        button_personal.setOnClickListener {
            val intent= Intent(requireContext(), PersonalInfoActivity::class.java)
            startActivity(intent)
        }

        val button_exit=view.findViewById<Button>(R.id.exituser_button)
        button_exit.setOnClickListener {
//            if(showAlertDialog()){
//                val intent= Intent(requireContext(), LoginActivity::class.java)
//                startActivity(intent)
//            }

            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder
                .setMessage("确认退出吗？")
                .setTitle("警告")
                .setPositiveButton("确认") { dialog, which ->
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                }
                .setNegativeButton("取消") { dialog, which ->
                    Toast.makeText(requireContext(),"取消退出",Toast.LENGTH_SHORT).show()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        lost.text = "This is Home Fragment"
    }

    private fun showAlertDialog() : Boolean{
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        var flag =0
        builder
            .setMessage("确认退出吗？")
            .setTitle("警告")
            .setPositiveButton("确认") { dialog, which ->
                flag=1
            }
            .setNegativeButton("取消") { dialog, which ->
                flag=0
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()

        if(flag==0){
            return false
        }
        return true
    }
}