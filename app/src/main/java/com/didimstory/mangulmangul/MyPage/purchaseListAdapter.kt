package com.didimstory.mangulmangul.MyPage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.buyListItem
import com.didimstory.mangulmangul.Entity.purchaseList
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.ActivityYoutubThumbNaiLBinding
import com.didimstory.mangulmangul.databinding.PurchaseListListItemBinding
import com.didimstory.mangulmangul.famous.youtubeFamous
import com.didimstory.mangulmangul.youtube.YoutubeItem
import com.didimstory.mangulmangul.youtube.youtubeTest

class purchaseListAdapter(var context: Context?, var test:Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    val mContext=context
    var dataurl:String?=null
    var dataList = listOf<buyListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = PurchaseListListItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
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

    inner class MainMusicHolder(val binding : PurchaseListListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: buyListItem) {

            binding.deliveryStatus.text=data.deliveryStatus
            binding.createdAt.text=data.createdAt

            binding.company.text=data.deliveryCompany
            binding.num
                .text=data.deliveryNumber

binding.mm.text=data.totalPrice

            val s = itemView.findViewById<RecyclerView>(R.id.recycler)
            val mAdapter = mContext?.let { purchaseListSubAdapter(it,data.artKitList) }
            s.adapter = mAdapter
            val layoutmanager = LinearLayoutManager(context)
            s.layoutManager = layoutmanager
            s.setHasFixedSize(true)




                }

            }



        }

