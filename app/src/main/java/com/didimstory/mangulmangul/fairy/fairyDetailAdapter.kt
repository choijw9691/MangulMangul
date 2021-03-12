package com.didimstory.mangulmangul.fairy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.databinding.ActivityYoutubThumbNaiLBinding
import com.didimstory.mangulmangul.databinding.FaityDetailItemBinding
import com.didimstory.mangulmangul.famous.youtubeFamous
import com.didimstory.mangulmangul.youtube.YoutubeItem
import com.didimstory.mangulmangul.youtube.youtubeTest

class fairyDetailAdapter(var context: Context?,var test: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    val mContext=context
    var dataurl:String?=null
    var dataList = listOf<YoutubeItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FaityDetailItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
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

    inner class MainMusicHolder(val binding : FaityDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: YoutubeItem) {



            mContext?.let {
                dataurl=data.ytUrl
                Glide.with(it)
                    .load( "https://img.youtube.com/vi/$dataurl/maxresdefault.jpg" )
                    .centerInside()
                    .override(1000,1000)
                    .into(binding.thumbnail)

                binding.fairyText.setText(data.title)
                binding.thumbnail.setOnClickListener(View.OnClickListener {
                    var intent:Intent?=null
                    Log.d("data.ytUrl",data.ytUrl)

                    if(test==0){

                        intent= Intent(mContext, youtubeTest::class.java)
                    }
                    else if(test==1){

                        intent=Intent(mContext, youtubeFamous::class.java)
                    }

                    intent?.putExtra("data.url",data.ytUrl)


                    intent?.addFlags(FLAG_ACTIVITY_NEW_TASK)
                    mContext.startActivity(intent)











                })

            }


/*            if(adapterPosition==0 || adapterPosition == dataList.size-1){
                binding.itemLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)

                val displayMetrics = mContext!!.resources.displayMetrics
                val screenWidth = displayMetrics.widthPixels
                var mLayoutParam : RecyclerView.LayoutParams =  binding.itemLayout.layoutParams as RecyclerView.LayoutParams
                if(adapterPosition == 0)
                    mLayoutParam.leftMargin = (screenWidth - binding.itemLayout.measuredWidthAndState)/2
                else
                    mLayoutParam.rightMargin = (screenWidth - binding.itemLayout.measuredWidthAndState)/2
            }*/
        }

    }



}