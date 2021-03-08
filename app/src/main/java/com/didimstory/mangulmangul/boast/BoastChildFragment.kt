package com.didimstory.mangulmangul.boast

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangulmangul.Entity.BoastChildItem
import com.didimstory.mangulmangul.Entity.boastRecycleItemData
import com.didimstory.mangulmangul.databinding.FragmentBoastChildBinding


class BoastChildFragment : Fragment() {

    private val GET_GALLERY_IMAGE = 200
    private var binding: FragmentBoastChildBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastAdapter:BoastChildAdapter
    private var dataList = arrayListOf<BoastChildItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBoastChildBinding.inflate(inflater, container, false)
        val view = binding?.root

        binding?.childImageBtn?.setOnClickListener(View.OnClickListener {

            var intent: Intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_GALLERY_IMAGE)


        })



        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
   if(requestCode==GET_GALLERY_IMAGE&&resultCode==Activity.RESULT_OK&&data!=null&&data.data!=null){

       var selectedImageUri=data.data
       Log.d("selectedImageUri",selectedImageUri.toString())
   dataList.add(BoastChildItem(selectedImageUri))
       mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
       boastAdapter =
           BoastChildAdapter(context,object : BoastChildAdapter.removeListener{
               override fun childRemove(position: Int) {
                   Log.d("remove",position.toString())
                   dataList.removeAt(position)

               }


           })

       binding!!.writeRecylcer.apply {
           this.layoutManager =
               mLayoutManager
           this.adapter = boastAdapter

       }

       boastAdapter.dataList =
           dataList
   }
    }

    companion object {
        fun newInstance(): BoastChildFragment? {
            var fragment: BoastChildFragment = BoastChildFragment()
            return fragment
        }
    }


}