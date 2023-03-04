package com.prashant830.jmtest.core.uimvvmlayer.secondui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prashant830.jmtest.R
import com.prashant830.jmtest.core.dblayer.Entity

class CatDetailIconRecycle  (val context: Context, private val mList: MutableList<Entity>) : RecyclerView.Adapter<CatDetailIconRecycle.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.cat_detail_icon_recy_card, parent , false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.iconID.text = mList[position].iconID.toString()
        var value = mList[position].tags.toString()
        holder.icontags.text = value.substring( 1, value.length - 1 )
        var v = mList[position].isPremium.toString()
        if(v != "true"){
            holder.iconPrem.text = "Download"
        }


        val format = mList[position].preveiwUrl

        Glide.with(context).load(format).centerCrop().into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val iconID : TextView = ItemView.findViewById(R.id.icon_Id)
        val icontags : TextView = ItemView.findViewById(R.id.ImageDetail)
        val iconPrem : TextView = ItemView.findViewById(R.id.icon_premium)
        val imageView = ItemView.findViewById<ImageView>(R.id.iconImage)


    }
}