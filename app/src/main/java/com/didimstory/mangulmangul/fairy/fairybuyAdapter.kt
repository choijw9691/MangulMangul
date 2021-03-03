package com.didimstory.mangulmangul.fairy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.didimstory.mangulmangul.databinding.FairyBuyItemBinding
import com.didimstory.mangulmangul.youtube.YoutubeItem
import com.didimstory.mangulmangul.youtube.youtubeTest


class fairybuyAdapter(var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{


    val mContext=context
    var dataurl:String?=null
    var dataList = listOf<fairybuyItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FairyBuyItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
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

    inner class MainMusicHolder(val binding : FairyBuyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: fairybuyItem) {

            mContext?.let {


                dataurl=data.imageview
                Glide.with(it)
                    .load( "https://img.youtube.com/vi/$dataurl/maxresdefault.jpg")
                    .centerInside()
                    .override(1000,1000)
                    .into(binding.imageview)



                binding.textview.setText(data.title)
                binding.checkbox.setText(data.check)



    /*            binding.checkbox.setOnClickListener(View.OnClickListener {

                    val intent=Intent(mContext,youtubeTest::class.java)
                    intent.putExtra("data.url","data.url")
                    mContext.startActivity(intent)



                })*/

            }



        }

    }



}