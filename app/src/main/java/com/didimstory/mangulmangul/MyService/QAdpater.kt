package com.didimstory.mangulmangul.MyService

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangulmangul.Entity.noticeDetailItem
import com.didimstory.mangulmangul.Entity.questionDetailItem
import com.didimstory.mangulmangul.databinding.QuestionItemBinding


class QAdpater (var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mContext = context
    var dataurl: String? = null
    var dataList = listOf<questionDetailItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = QuestionItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
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

    inner class MainMusicHolder(val binding: QuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: questionDetailItem) {

            mContext?.let {


                binding.questionStatue.setText(data.statue)
                binding.questionTitle.setText(data.title)
                binding.questionDate.setText(data.date)

                binding.noticeLn.setOnClickListener(View.OnClickListener {
                    Log.d("왔다","왔다")
                    val intent = Intent(mContext, QuestionDetailActivity::class.java)
                    intent.putExtra("title", data?.title)
                    intent.putExtra("date", data?.date)
                    intent.putExtra("statue", data?.statue)
                    intent.putExtra("question", data?.question)
                    intent.putExtra("answer", data?.answer)
                    mContext.startActivity(intent)


                })

            }


        }

    }


}