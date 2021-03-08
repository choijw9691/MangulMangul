package com.didimstory.mangulmangul.boast

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.boastDetailItem
import com.didimstory.mangulmangul.R


class ViewHolderPage : RecyclerView.ViewHolder {
private lateinit var imageView:ImageView
    private lateinit var rl_layout:LinearLayout
    lateinit var data:boastDetailItem
  var itemView1:View
    constructor(itemView: View) : super(itemView){
        itemView1=itemView
      imageView = itemView.findViewById(R.id.viewpager_imageview)
       rl_layout= itemView.findViewById(R.id.rl_layout)
    }
    fun onBind(data:boastDetailItem){
        this.data=data

Log.d("itemView1",data.imageview)

        Glide.with(itemView1).load(data.imageview)
            .centerInside().override(1000, 1000).into(imageView)

    }


}