package com.didimstory.mangulmangul.boast

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.BoastChildItem
import com.didimstory.mangulmangul.databinding.BoastChildItemBinding
import com.didimstory.mangulmangul.databinding.BoastDetailRecycleItemBinding

class BoastChildAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>
{

var mremoveListener:removeListener?
    var dataurl:Uri?=null
    var mContext: Context?
    var dataList = listOf<BoastChildItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    constructor(
        context: Context?
    ,
        mremoveListener:removeListener
    ) : super() {

        this.mContext = context
this.mremoveListener=mremoveListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = BoastChildItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
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

    inner class MainMusicHolder(val binding : BoastChildItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: BoastChildItem) {



            mContext?.let {
                dataurl=data.imageUri
                Glide.with(it)
                    .load(dataurl)
                    .centerInside()
                    .override(1000,1000)
                    .into(binding.thumbnail)


            }
binding.childRemove.setOnClickListener(View.OnClickListener {
    mremoveListener?.childRemove(adapterPosition)

    //dataList-=dataList[adapterPosition]
notifyItemRemoved(adapterPosition)
    notifyItemRangeChanged(adapterPosition,dataList.size)

})

        }

    }

    interface removeListener {
        fun childRemove(position: Int)

    }

}