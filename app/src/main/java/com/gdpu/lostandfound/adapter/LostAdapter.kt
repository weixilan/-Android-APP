package com.gdpu.lostandfound.adapter

import android.content.Context
import android.content.Intent
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.activity.DetailsActivity
import com.gdpu.lostandfound.db.LAFDatabaseHelper
import com.gdpu.lostandfound.home.HomeActivity
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

        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(parent.context,DetailsActivity::class.java)
            intent.putExtra("type","lost")
            intent.putExtra("position","${viewHolder.bindingAdapterPosition+1}")
            parent.context.startActivity(intent)
        }

        return viewHolder
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