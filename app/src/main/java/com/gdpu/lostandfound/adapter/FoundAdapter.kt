package com.gdpu.lostandfound.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.activity.DetailsActivity
import com.gdpu.lostandfound.pojo.Found

class FoundAdapter(val foundList:List<Found>) : RecyclerView.Adapter<FoundAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val foundImage :ImageView=view.findViewById(R.id.foundImage)
        val foundName : TextView=view.findViewById(R.id.foundName)
        val foundTime : TextView=view.findViewById(R.id.foundTime)
        val foundAddress : TextView=view.findViewById(R.id.foundAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_found,parent,false)

        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(parent.context, DetailsActivity::class.java)
            intent.putExtra("type","found")
            intent.putExtra("position","${viewHolder.bindingAdapterPosition+1}")
            parent.context.startActivity(intent)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val found = foundList[position]
        holder.foundImage.setImageResource(found.imageId)
        holder.foundName.text=found.name
        holder.foundTime.text=found.time
        holder.foundAddress.text=found.address
    }

    override fun getItemCount() = foundList.size
}