package com.didimstory.mangulmangul.Purchase

import android.R
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.databinding.PurchaseItemBinding
import com.didimstory.mangulmangul.fairy.KitViewPagerAdapter
import com.google.android.material.internal.ContextUtils.getActivity
import java.text.DecimalFormat


class purchaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>{
    var formatter: DecimalFormat = DecimalFormat("###,###")
    var mContext: Context
var mbuyTextListener:buyTextListener?

var aa:ArrayList<String>? =null

    constructor(
        context: Context,
        mbuyTextListener:buyTextListener?
    ) : super() {

        this.mContext = context
this.mbuyTextListener=mbuyTextListener
    }

    var dataurl: String? = null
    var dataList = listOf<Buy>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    //   var checkboxList = arrayListOf<checkboxData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = PurchaseItemBinding.inflate(LayoutInflater.from(mContext), parent, false)

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

    inner class MainMusicHolder(val binding: PurchaseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Buy, num: Int) {

            mContext?.let {

                binding.purchaseImageview.adapter= KitViewPagerAdapter(mContext,data.url)
                binding.indicator.setViewPager(binding.purchaseImageview)
                binding.indicator.createIndicators(data.url.size,0)


                binding.purchaseTitle.text = data.title
                binding.purchaseText.setText(formatter.format((data.price).toInt()) + "원")

                binding.purchaseImageview.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
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

                        binding.indicator.animatePageSelected(position%data.url.size)

                    }
                })


                binding.purcaseButton.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {







                        if((data.price.toInt()*position)==0){

    binding.purchaseText.setText(formatter.format((data.price).toInt()) + "원")
data.count=1
}else{

    binding.purchaseText.setText(formatter.format(( (data.price.toInt()*(position+1)).toString()).toInt()) + "원")

 //   binding.purchaseText.text.toString()
    data.count=(position+1)

}
                        mbuyTextListener?.buyTotal()


                    }


                }




            }






        }


    }

    interface buyTextListener {
        fun buyTotal()

    }


}