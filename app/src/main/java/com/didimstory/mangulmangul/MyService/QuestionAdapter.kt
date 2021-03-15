package com.didimstory.mangulmangul.MyService

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.boastDetailItem
import com.didimstory.mangulmangul.Entity.boastRecycleItemData
import com.didimstory.mangulmangul.Entity.noticeDetailItem
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.boast.ViewHolderPage
import com.didimstory.mangulmangul.boast.boastActivity
import com.didimstory.mangulmangul.databinding.BoastItemBinding
import com.didimstory.mangulmangul.databinding.NoticeItemBinding

class QuestionAdapter(var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mContext = context
    var dataurl: String? = null
    var dataList = listOf<noticeDetailItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = NoticeItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
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

    inner class MainMusicHolder(val binding: NoticeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: noticeDetailItem) {

            mContext?.let {


                binding.noticeId.setText(data.title)
           /*     binding.noticeDate.setText(data.date)
                binding.noticeLn.setOnClickListener(View.OnClickListener {

                    val intent = Intent(mContext, NoticeDetailActivity::class.java)
                    intent.putExtra("title", data?.title)
                    intent.putExtra("date", data?.date)
                    intent.putExtra("content", data?.content)
                    if(data?.image!="null"){
                        intent.putExtra("image", data?.image)
                    }else{
                        intent.putExtra("image", "null")
                    }
                    mContext.startActivity(intent)*/

                binding.noticeDate.setText(data.createdAt)
                binding.noticeLn.setOnClickListener(View.OnClickListener {

                    val intent = Intent(mContext, NoticeDetailActivity::class.java)
                    intent.putExtra("title", data?.title)
                    intent.putExtra("date", data?.createdAt)
                    intent.putExtra("noticeIdx", data?.noticeIdx)
                    mContext.startActivity(intent)
                })

            }


        }

    }


}