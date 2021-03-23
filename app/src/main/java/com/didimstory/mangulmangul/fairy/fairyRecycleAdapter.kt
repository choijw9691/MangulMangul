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
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.`object`.likeobject
import com.didimstory.mangulmangul.boast.boastActivity
import com.didimstory.mangulmangul.databinding.ActivityYoutubThumbNaiLBinding
import com.didimstory.mangulmangul.famous.youtubeFamous
import com.didimstory.mangulmangul.youtube.YoutubeItem
import com.didimstory.mangulmangul.youtube.youtubeTest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class fairyRecycleAdapter(var context: Context?, var test: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() , likeobject{

    val mContext = context
    var dataurl: String? = null
    var dataList = listOf<YoutubeItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ActivityYoutubThumbNaiLBinding.inflate(LayoutInflater.from(mContext), parent, false)
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

    inner class MainMusicHolder(val binding: ActivityYoutubThumbNaiLBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(data: YoutubeItem) {


            if (data.likeStatus == true) {
Log.d("ss1111111",data.likeStatus.toString())
                binding.heart.progress=1f

            } else {
                Log.d("ss111112",data.likeStatus.toString())
                binding.heart.progress=0f
            }
binding.heart.setOnClickListener(View.OnClickListener {

    if (mContext != null) {


        if(data.likeStatus==true){
            Log.d("ss111113",data.likeStatus.toString())
            // binding.heart.frame = binding.heart.minFrame.toInt()
            binding.heart.pauseAnimation()
            binding.heart.progress=0f

        }else{

            binding.heart.playAnimation()
            binding.heart.loop(false);

        }




        Client.retrofitService.updateLike(
            PreferenceManager.getLong(
                mContext,
                "PrefIDIndex"
            ),data.engFairyTaleIdx.toLong()
        ).enqueue(object :
            Callback<Boolean> {


            override fun onFailure(call: Call<Boolean>, t: Throwable) {

            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>)  {

                data.likeStatus=response.body()!!


            }

        })




    }


})

















            binding.thumbnail.clipToOutline = true

            if (test != 3) {
                mContext?.let {
                    dataurl = data.ytUrl
                    Glide.with(it)
                        .load("https://img.youtube.com/vi/$dataurl/maxresdefault.jpg")
                        .centerInside()
                        .override(1000, 1000)
                        .into(binding.thumbnail)

                    binding.fairyText.setText(data.title)
                    binding.thumbnail.setOnClickListener(View.OnClickListener {

                        var intent: Intent? = null


                        if (test == 0) {
                            intent = Intent(mContext, youtubeTest::class.java)
                            intent?.putExtra("engFairyTaleIdx", data.engFairyTaleIdx)
                        } else if (test == 1) {

                            intent = Intent(mContext, youtubeFamous::class.java)
                        }

                        intent?.putExtra("data.url", data.ytUrl)
                        intent?.putExtra("engFairyTaleIdx", data.engFairyTaleIdx)
                        intent?.putExtra("likeStatus", data.likeStatus)
                        intent?.putExtra("title", data.title)
                        mContext.startActivity(intent)


                    })

                }

            } else {


                binding.heart.visibility = View.GONE
                mContext?.let {
                    dataurl = data.ytUrl
                    Glide.with(it)
                        .load(dataurl)
                        .centerInside()
                        .override(1000, 1000)
                        .into(binding.thumbnail)

                    binding.fairyText.setText(data.title)


                }

                binding.thumbnail.setOnClickListener(View.OnClickListener {
                    Log.d("ss11111478",data.engFairyTaleIdx.toString())
                    val intent = Intent(mContext, boastActivity::class.java)
                    intent.putExtra("boastIdx", data.engFairyTaleIdx.toInt())
                    mContext?.startActivity(intent)

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


