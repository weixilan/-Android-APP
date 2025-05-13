package com.gdpu.lostandfound.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.pojo.OldPub

class OldPubAdapter(val oldPubList:List<OldPub>) : RecyclerView.Adapter<OldPubAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val oldPubImage :ImageView=view.findViewById(R.id.oldPubImage)
        val oldPubName : TextView=view.findViewById(R.id.oldPubName)
        val oldPubTime : TextView=view.findViewById(R.id.oldPubTime)
        val oldPubAddress : TextView=view.findViewById(R.id.oldPubAddress)
//        val oldPubUpdate : TextView=view.findViewById(R.id.oldPubUpdate)
//        val oldPubDelete : TextView=view.findViewById(R.id.oldPubDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_old_pub,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val oldPub = oldPubList[position]
        holder.oldPubImage.setImageResource(oldPub.imageId)
        holder.oldPubName.text=oldPub.name
        holder.oldPubTime.text=oldPub.time
        holder.oldPubAddress.text=oldPub.address
//        holder.oldPubUpdate.
    }

    override fun getItemCount() = oldPubList.size
}