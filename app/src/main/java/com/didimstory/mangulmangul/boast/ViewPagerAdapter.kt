package com.didimstory.mangulmangul.boast

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangulmangul.Entity.boastDetailItem
import com.didimstory.mangulmangul.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewHolderPage> {

    private var listData: ArrayList<boastDetailItem>?=null

    constructor(listData: ArrayList<boastDetailItem>?) : super() {
        this.listData = listData

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPage {
        var context: Context = parent.context
        var view: View =

            LayoutInflater.from(context).inflate(R.layout.item_viewpager, parent, false)


        return ViewHolderPage(view)
    }

    override fun getItemCount(): Int {
        return listData?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolderPage, position: Int) {

        listData?.get(position)?.let { holder.onBind(it) }

    }


}