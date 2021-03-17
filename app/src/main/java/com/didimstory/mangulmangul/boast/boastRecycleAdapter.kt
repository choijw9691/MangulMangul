package com.didimstory.mangulmangul.boast

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.boastRecycleItemData
import com.didimstory.mangulmangul.databinding.ActivityYoutubThumbNaiLBinding
import com.didimstory.mangulmangul.databinding.BoastItemBinding


class boastRecycleAdapter(var context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mContext = context
    var dataurl: String? = null
    var dataList = listOf<boastRecycleItemData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = BoastItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
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

    inner class MainMusicHolder(val binding: BoastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: boastRecycleItemData) {


            if (data.likeStatus==true){

                binding.heart.frame= binding.heart.maxFrame.toInt()
                binding.heart.setOnClickListener(View.OnClickListener {
                    binding.heart.frame= binding.heart.minFrame.toInt()
                })
            }
            else{

                binding.heart.frame=binding.heart.minFrame.toInt()

                binding.heart.setOnClickListener(View.OnClickListener {
                    binding.heart.playAnimation()
                    binding.heart.loop(false);
                })
            }





            binding.itemImage.clipToOutline=true


            mContext?.let {
                dataurl = data.fileRealName
                Glide.with(it)
                    .load(dataurl)
                    .centerInside()
                    .override(1000, 1000)
                    .into(binding.itemImage)

                binding.imageText.setText(data.title)
                binding.itemImage.setOnClickListener(View.OnClickListener {

                    val intent = Intent(mContext, boastActivity::class.java)
                    intent.putExtra("data.url", data.fileRealName)
                    intent.putExtra("content", data.content)
                    intent.putExtra("title", data.title)
                    intent.putExtra("nickname", data.nickname)
                    intent.putExtra("likeStatus", data.likeStatus)
                    intent.putExtra("boastIdx", data.boastIdx)
                    mContext.startActivity(intent)


                })

            }


        }

    }


}