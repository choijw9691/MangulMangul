package com.didimstory.mangulmangul.boast


import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.didimstory.mangulmangul.databinding.BoastDetailRecycleItemBinding


class boastDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {


    var dataurl: String? = null
    var mContext: Context?
    var dataList = listOf<boastDetailRecycleItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var mBoastDetailListener: BoastDetailListener?

    constructor(
        context: Context?,
        mBoastDetailListener: boastDetailAdapter.BoastDetailListener?

    ) : super() {
        this.mBoastDetailListener = mBoastDetailListener
        this.mContext = context

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            BoastDetailRecycleItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MainMusicHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainMusicHolder -> {
                holder.bind(dataList[position])
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {


        when (holder) {
            is MainMusicHolder -> {
                holder.bind(dataList[position])
            }
        }
    }


    override fun getItemCount(): Int = dataList.size

    inner class MainMusicHolder(val binding: BoastDetailRecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: boastDetailRecycleItem) {
            binding.thumbnail.clipToOutline = true

            mContext?.let {
                dataurl = data.fileRealName
                Glide.with(it)
                    .load(dataurl)
                    .centerInside()
                    .override(1000, 1000)
                    .into(binding.thumbnail)



                binding.thumbnail.setOnClickListener(View.OnClickListener {

                    mBoastDetailListener?.detailListener(
                        data.boastIdx,
                        data.fileRealName,
                        data.likeStatus,
                        data.nickname,
                        data.title,
                        data.contents
                    )


                })

            }


        }

    }

    interface BoastDetailListener {
        fun detailListener(
            boastIdx: Int,
            fileRealName: String?,
            likeStatus: Boolean,
            nickname: String?,
            title: String?,
            content: String?
        )

    }

}