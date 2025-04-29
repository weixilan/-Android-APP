package com.gdpu.lostandfound.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.pojo.Message

class MessageAdapter(val messageList:List<Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val messageImage :ImageView=view.findViewById(R.id.messageImage)
        val messageName : TextView=view.findViewById(R.id.messageName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messageList[position]
        holder.messageImage.setImageResource(message.imageId)
        holder.messageName.text=message.name
    }

    override fun getItemCount() = messageList.size
}