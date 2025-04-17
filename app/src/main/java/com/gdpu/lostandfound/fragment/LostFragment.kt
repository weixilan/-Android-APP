package com.gdpu.lostandfound.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.adapter.LostAdapter
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_lost, container, false)
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

    private fun initLost() {
        repeat(2) {
            lostList.add(Lost("1", R.drawable.iconmonstr_mail_thin_240))
            lostList.add(Lost("2", R.drawable.iconmonstr_mail_thin_240))
            lostList.add(Lost("3", R.drawable.iconmonstr_mail_thin_240))
            lostList.add(Lost("4", R.drawable.iconmonstr_mail_thin_240))
            lostList.add(Lost("5", R.drawable.iconmonstr_mail_thin_240))
            lostList.add(Lost("6", R.drawable.iconmonstr_mail_thin_240))
            lostList.add(Lost("7", R.drawable.iconmonstr_mail_thin_240))
        }
    }
}