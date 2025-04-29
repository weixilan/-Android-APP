package com.gdpu.lostandfound.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.pojo.Lost

class LostAdapter(val lostList:List<Lost>) : RecyclerView.Adapter<LostAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val lostImage :ImageView=view.findViewById(R.id.lostImage)
        val lostName : TextView=view.findViewById(R.id.lostName)
        val lostTime : TextView=view.findViewById(R.id.lostTime)
        val lostAddress : TextView=view.findViewById(R.id.lostAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lost,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lost = lostList[position]
        holder.lostImage.setImageResource(lost.imageId)
        holder.lostName.text=lost.name
        holder.lostTime.text=lost.time
        holder.lostAddress.text=lost.address
    }

    override fun getItemCount() = lostList.size
}