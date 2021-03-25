package com.didimstory.mangulmangul.fairy

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.Entity.fairybuyItem
import com.didimstory.mangulmangul.databinding.FairyBuyItemBinding
import java.text.DecimalFormat


class fairybuyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    var checkboxtext: String? = null

    var max: Int = 0
    val imageList = ArrayList<String>()

    var checkboxSelectedList = ArrayList<Buy>()
    var titleSelectedList = ArrayList<String>()
    var purchaseSelectedList = ArrayList<String>()
    var formatter: DecimalFormat = DecimalFormat("###,###")
    var mContext: Context
    var mbuyTextListener: buyTextListener?

    constructor(
        context: Context,
        mbuyTextListener: buyTextListener?

    ) : super() {
        this.mbuyTextListener = mbuyTextListener
        this.mContext = context

    }

    var dataurl: String? = null
    var dataList = listOf<fairybuyItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var checkboxList = arrayListOf<checkboxData>()
    var checkList = arrayListOf<Boolean>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FairyBuyItemBinding.inflate(LayoutInflater.from(mContext), parent, false)

        return MainMusicHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is MainMusicHolder -> {
                holder.bind(dataList[position], position)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {

        // Log.d("adapterPosition",position.toString())

        when (holder) {
            is MainMusicHolder -> {
                holder.bind(dataList[position], position)
            }
        }
    }


    override fun getItemCount(): Int = dataList.size

    inner class MainMusicHolder(val binding: FairyBuyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: fairybuyItem, num: Int) {

            mContext?.let {



                if (num >= checkboxList.size) {

                    checkboxList.add(num, checkboxData(num, false))

                }




                binding.textview.setText(data.name)
                binding.checkbox.setText(formatter.format((data.price).toInt()) + "원")





                Log.d("넹",data.fileRealName.toString())

                binding.imageview.adapter=KitViewPagerAdapter(mContext,data.fileRealName)
                binding.indicator.setViewPager(binding.imageview)
                binding.indicator.createIndicators(data.fileRealName.size,0)

                binding.imageview.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                    }

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)

                        binding.indicator.animatePageSelected(position%data.fileRealName.size)
                    }
                })




                binding.checkbox.isChecked = checkboxList[num].checked



                binding.checkbox.setOnClickListener {

                    if (binding.checkbox.isChecked) {
                        binding.checkbox.isChecked = false
                        checkboxList[num].checked = false
                        checkboxtext =
                            binding.checkbox.text.toString().replace(("[^0-9]").toRegex(), "")
                        max -= Integer.parseInt(checkboxtext)
                        mbuyTextListener?.buyTotal(formatter.format(max) + "원")
                    } else {
                        binding.checkbox.isChecked = true
                        checkboxList[num].checked = true
                        checkboxtext =
                            binding.checkbox.text.toString().replace(("[^0-9]").toRegex(), "")
                        max += Integer.parseInt(checkboxtext)
                        mbuyTextListener?.buyTotal(formatter.format(max) + "원")
                    }
                }


                /*            binding.checkbox.setOnClickListener(View.OnClickListener {

                                val intent=Intent(mContext,youtubeTest::class.java)
                                intent.putExtra("data.url","data.url")
                                mContext.startActivity(intent)



                            })*/


            }

        }


    }

    interface buyTextListener {
        fun buyTotal(data: String?)
        fun buyList(purchase: ArrayList<Buy>)
    }



    fun maxplus() {
        for (i in 0 until checkboxList.size) {
            if (checkboxList.get(i).checked === true) {
                var a = dataList[i].fileRealName

                var purchase = Buy(

                    dataList[i].artKitIdx,
                    a,
                    dataList[i].name,
                    dataList[i].price,1
                )

                checkboxSelectedList.add(purchase)
            }
        }
        mbuyTextListener?.buyList(checkboxSelectedList)
        checkboxSelectedList.clear()
    }

}