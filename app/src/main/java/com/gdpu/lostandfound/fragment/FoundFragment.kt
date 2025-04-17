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
import com.gdpu.lostandfound.adapter.FoundAdapter
import com.gdpu.lostandfound.adapter.LostAdapter
import com.gdpu.lostandfound.pojo.Found

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

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_found, container, false)
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

    private fun initFound() {
        repeat(2) {
            foundList.add(Found("1", R.drawable.iconmonstr_mail_thin_240))
            foundList.add(Found("2", R.drawable.iconmonstr_mail_thin_240))
            foundList.add(Found("3", R.drawable.iconmonstr_mail_thin_240))
            foundList.add(Found("4", R.drawable.iconmonstr_mail_thin_240))
            foundList.add(Found("5", R.drawable.iconmonstr_mail_thin_240))
            foundList.add(Found("6", R.drawable.iconmonstr_mail_thin_240))
            foundList.add(Found("7", R.drawable.iconmonstr_mail_thin_240))
        }
    }
}