package com.didimstory.mangulmangul.boast

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.databinding.ActivityBoastBinding
import com.didimstory.mangulmangul.databinding.ActivityYoutubThumbNaiLBinding
import com.didimstory.mangulmangul.databinding.BoastDetailRecycleItemBinding
import com.didimstory.mangulmangul.fairy.fairybuyAdapter
import com.didimstory.mangulmangul.youtube.YoutubeItem
import com.didimstory.mangulmangul.youtube.youtubeTest

class boastDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>
{


    var dataurl:String?=null
    var mContext: Context
    var dataList = listOf<boastDetailRecycleItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var mBoastDetailListener: BoastDetailListener?
    constructor(
        context: Context,
        mBoastDetailListener: boastDetailAdapter.BoastDetailListener?

    ) : super() {
        this.mBoastDetailListener = mBoastDetailListener
        this.mContext = context

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = BoastDetailRecycleItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MainMusicHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MainMusicHolder -> {
                holder.bind(dataList[position])
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads:List<Any>) {


        when(holder) {
            is MainMusicHolder -> {
                holder.bind(dataList[position])
            }
        }
    }


    override fun getItemCount(): Int = dataList.size

    inner class MainMusicHolder(val binding : BoastDetailRecycleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: boastDetailRecycleItem) {



            mContext?.let {
                dataurl=data.imageview
                Glide.with(it)
                    .load( "https://img.youtube.com/vi/$dataurl/maxresdefault.jpg" )
                    .centerInside()
                    .override(1000,1000)
                    .into(binding.thumbnail)


                binding.thumbnail.setOnClickListener(View.OnClickListener {

        mBoastDetailListener?.detailListener("https://img.youtube.com/vi/$dataurl/maxresdefault.jpg",data.nickname,data.content)


                })

            }


        }

    }

    interface BoastDetailListener {
        fun detailListener(thumnail: String?,nickname: String?,content: String?)

    }

}