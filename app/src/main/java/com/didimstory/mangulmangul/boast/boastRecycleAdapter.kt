package com.didimstory.mangulmangul.boast

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.boastRecycleItemData
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.databinding.ActivityYoutubThumbNaiLBinding
import com.didimstory.mangulmangul.databinding.BoastItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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


            if (data.likeStatus == true) {
                Log.d("ss1111111",data.likeStatus.toString())
                binding.heart.progress=1f

            } else {
                Log.d("ss111112",data.likeStatus.toString())
                binding.heart.progress=0f
            }
            binding.heart.setOnClickListener(View.OnClickListener {

                if (mContext != null) {


                    if (data.likeStatus == true) {
                        Log.d("ss111113", data.likeStatus.toString())
                        // binding.heart.frame = binding.heart.minFrame.toInt()
                        binding.heart.pauseAnimation()
                        binding.heart.progress = 0f

                    } else {
                        Log.d("ss111114", data.likeStatus.toString())
                        binding.heart.playAnimation()
                        binding.heart.loop(false);

                    }




                    Client.retrofitService.boastupdateLike(
                        PreferenceManager.getLong(
                            mContext,
                            "PrefIDIndex"
                        ), data.boastIdx.toLong()
                    ).enqueue(object :
                        Callback<Boolean> {


                        override fun onFailure(call: Call<Boolean>, t: Throwable) {

                        }

                        override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                         //   Log.d("sslssl",response.body()!!.toString())
                           data.likeStatus = response.body()!!


                        }

                    })
                }})


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