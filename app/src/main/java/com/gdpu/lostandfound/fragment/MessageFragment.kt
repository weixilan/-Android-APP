package com.gdpu.lostandfound.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.adapter.LostAdapter
import com.gdpu.lostandfound.adapter.MessageAdapter
import com.gdpu.lostandfound.pojo.Lost
import com.gdpu.lostandfound.pojo.Message

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MessageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MessageFragment : Fragment() {
    private val messageList = ArrayList<Message>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_message, container, false)
        initMessage()
        val rv=view.findViewById<RecyclerView>(R.id.recyclerView_Message)
        val layoutManager= LinearLayoutManager(requireContext())
        rv.layoutManager=layoutManager
        val adapter= MessageAdapter(messageList)
        rv.adapter=adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initMessage() {
        repeat(2) {
            messageList.add(Message("1", R.drawable.iconmonstr_mail_thin_240))
            messageList.add(Message("2", R.drawable.iconmonstr_mail_thin_240))
            messageList.add(Message("3", R.drawable.iconmonstr_mail_thin_240))
            messageList.add(Message("4", R.drawable.iconmonstr_mail_thin_240))
            messageList.add(Message("5", R.drawable.iconmonstr_mail_thin_240))
            messageList.add(Message("6", R.drawable.iconmonstr_mail_thin_240))
            messageList.add(Message("7", R.drawable.iconmonstr_mail_thin_240))
        }
    }
}