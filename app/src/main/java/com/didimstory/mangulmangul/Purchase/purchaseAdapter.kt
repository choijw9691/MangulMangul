package com.didimstory.mangulmangul.Purchase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.databinding.PurchaseItemBinding




class purchaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>{

    var mContext: Context

var aa:ArrayList<String>? =null
    constructor(
        context: Context
    ) : super() {

        this.mContext = context

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


                dataurl = data.url
                Glide.with(it)
                    .load("https://img.youtube.com/vi/$dataurl/maxresdefault.jpg")
                    .centerInside()
                    .override(1000, 1000)
                    .into(binding.purchaseImageview)

                binding.purchaseTitle.text = data.title
                binding.purchaseText.text = data.price.toString()
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

if((data.price*position)==0){



    binding.purchaseText.text = data.price.toString()
data.count=1
}else{


    binding.purchaseText.text = (data.price*(position+1)).toString()
    binding.purchaseText.text.toString()
    data.count=(position+1)

}



                    }


                }




            }






        }


    }

    interface buyTextListener {
        fun buyTotal(data: String?)
        fun buyList(purchase: ArrayList<Buy>)
    }

    fun setpText(aa1:String){


    }

    fun getpText(): String {
        return aa.toString()
    }
}