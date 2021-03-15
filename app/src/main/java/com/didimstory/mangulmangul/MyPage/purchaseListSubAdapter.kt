package com.didimstory.mangulmangul.MyPage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.ArtKit
import com.didimstory.mangulmangul.Entity.buyListItem
import com.didimstory.mangulmangul.Entity.purchaseList
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.ActivityYoutubThumbNaiLBinding
import com.didimstory.mangulmangul.databinding.PurchaseListItemBinding
import com.didimstory.mangulmangul.databinding.PurchaseListListItemBinding
import com.didimstory.mangulmangul.famous.youtubeFamous
import com.didimstory.mangulmangul.youtube.YoutubeItem
import com.didimstory.mangulmangul.youtube.youtubeTest
import org.w3c.dom.Text

class purchaseListSubAdapter(private val context: Context, private val List: List<ArtKit>) :
    RecyclerView.Adapter<purchaseListSubAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.purchase_list_item, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(List[p1])
    }


    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val p = itemView?.findViewById<ImageView>(R.id.purchaseImageview)
        private val pp = itemView?.findViewById<TextView>(R.id.purchaseTitle)
        private val ppp = itemView?.findViewById<TextView>(R.id.count)
        private val pppp = itemView?.findViewById<TextView>(R.id.money)
        fun bind(count: ArtKit) {

            p?.let {
                Glide.with(context)
                    .load(count.fileRealName )
                    .centerInside()
                    .override(1000,1000)
                    .into(it)
            }
            pp?.text = count.name.toString()
            ppp?.text=count.count.toString()
            pppp?.text=count.price.toString()
        }
    }
}