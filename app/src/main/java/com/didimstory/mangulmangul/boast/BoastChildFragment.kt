package com.didimstory.mangulmangul.boast

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.BoastChildItem
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentBoastChildBinding
import com.didimstory.mangulmangul.fragment.BoastFragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class BoastChildFragment : Fragment() {
var file1:File?=null
    private val GET_GALLERY_IMAGE = 200
    private var binding: FragmentBoastChildBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastAdapter: BoastChildAdapter
    private var dataList = arrayListOf<BoastChildItem>()



    val TAKE_PICTURE = 1

    // 경로 변수와 요청변수 생성
    var mCurrentPhotoPath: String? = null
    val REQUEST_TAKE_PHOTO = 1

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

dispatchTakePictureIntent()


        })


        binding?.boastBack?.setOnClickListener(View.OnClickListener {

            (activity as MainActivity).setOnBackPressedListener(null)

            childFragmentManager.popBackStackImmediate()
            var parentFragment: FragmentManager =parentFragmentManager
            parentFragment.beginTransaction()
                .replace(R.id.boastContainer, BoastFragment())
                .commit()
        })


        binding?.boastInsert?.setOnClickListener(View.OnClickListener {

            Toast.makeText(activity, "등록되었습니다.", Toast.LENGTH_SHORT).show()


            var requestFile = file1
val requestBody=requestFile?.asRequestBody("image/jpeg".toMediaTypeOrNull())

val multipart=
    requestBody?.let { it1 -> MultipartBody.Part.createFormData("image",requestFile?.name, it1) }



            if (multipart != null) {
                Client.retrofitService.uploadImage(multipart)
                    .enqueue(object :
                        Callback<Void> {
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Log.d("에러네",t.toString())
                            Log.d("에러네", Uri.fromFile(requestFile).toString()
                            )
                        }

                        override fun onResponse(call: Call<Void>, response: Response<Void>) {

                        }

                    })
            }
            else Log.d("에러네","에러네")







            (activity as MainActivity).setOnBackPressedListener(null)

            childFragmentManager.popBackStackImmediate()
            var parentFragment: FragmentManager =parentFragmentManager
            parentFragment.beginTransaction()
                .replace(R.id.boastContainer, BoastFragment())
                .commit()
        })


        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null) {

            var selectedImageUri = data.data
            Log.d("selectedImageUri", selectedImageUri.toString())
            dataList.add(BoastChildItem(selectedImageUri))

         file1= File(selectedImageUri.toString())

            mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            boastAdapter =
                BoastChildAdapter(context, object : BoastChildAdapter.removeListener {
                    override fun childRemove(position: Int) {
                        Log.d("remove", position.toString())
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

    // 사진 촬영 후 썸네일만 띄워줌. 이미지를 파일로 저장해야 함
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = requireContext()?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )
       // mCurrentPhotoPath = image.getAbsolutePath()
        return image
    }
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (activity?.packageManager?.let { takePictureIntent.resolveActivity(it) } != null)
        {
            var photoFile: File? = null
            try
            {
                photoFile = createImageFile()
            }
            catch (ex:IOException) {}
            if (photoFile != null)
            {



                Log.d("photoFile",Uri.fromFile(photoFile).toString())
file1=photoFile
/*
                val photoURI =
                    FileProvider.getUriForFile(Objects.requireNonNull(requireContext()),
                        BuildConfig.APPLICATION_ID + ".provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)*/

                var intent: Intent = Intent(Intent.ACTION_PICK)
                intent.setDataAndType(
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "image/*"
                )
                startActivityForResult(intent, GET_GALLERY_IMAGE)
            }
        }
    }


}