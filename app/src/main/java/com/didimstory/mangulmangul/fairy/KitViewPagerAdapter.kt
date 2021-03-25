package com.didimstory.mangulmangul.fairy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.R

class KitViewPagerAdapter(private val context: Context, private val items: ArrayList<String>) :
    RecyclerView.Adapter<KitViewPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 그냥 단순 Test 므로 context 를 Activity 로 받았습니다
        Glide.with(context).load(items[position]).into(holder.imageUrl)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageUrl: ImageView = view.findViewById(R.id.viewpager_imageview)
    }
}