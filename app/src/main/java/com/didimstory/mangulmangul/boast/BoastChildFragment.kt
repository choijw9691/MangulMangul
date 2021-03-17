package com.didimstory.mangulmangul.boast

import android.Manifest
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import com.didimstory.mangulmangul.BuildConfig
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangul.Client

import com.didimstory.mangulmangul.Entity.BoastChildItem
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentBoastChildBinding
import com.didimstory.mangulmangul.fragment.BoastFragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.apache.commons.io.IOUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class BoastChildFragment : Fragment() {
    private val REQUEST_CAMERA = 100
    private val REQUEST_ALBUM = 101

    private var binding: FragmentBoastChildBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastAdapter: BoastChildAdapter
    private var dataList = arrayListOf<BoastChildItem>()
var file1:File?=null

    private var cacheFilePath:String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode === REQUEST_CAMERA)
        {
            for (g in grantResults)
            {
                if (g == PackageManager.PERMISSION_DENIED)
                {
                    //권한거부
                    return
                }
            }
            //임시파일 생성
            val file = createImgCacheFile()
            cacheFilePath = file.getAbsolutePath()
            //카메라 호출
            onCamera(REQUEST_CAMERA, file)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentBoastChildBinding.inflate(inflater, container, false)
        val view = binding?.root

        binding?.childImageBtn?.setOnClickListener(View.OnClickListener {



            //  requestPermissions(arrayOf<String>(Manifest.permission.CAMERA), REQUEST_CAMERA)

            onAlbum( REQUEST_ALBUM );



        })


        binding?.boastBack?.setOnClickListener(View.OnClickListener {

            //  (activity as MainActivity).setOnBackPressedListener(null)

            childFragmentManager.popBackStackImmediate()
            var parentFragment: FragmentManager =parentFragmentManager
            parentFragment.beginTransaction()
                .replace(R.id.boastContainer, BoastFragment())
                .commit()
        })


        binding?.boastInsert?.setOnClickListener(View.OnClickListener {





            var requestFile = file1

        //    Long.toString(requestFile.length+"바이트")

            val requestBody=requestFile?.asRequestBody("image/jpeg".toMediaTypeOrNull())

            val multipart=
                requestBody?.let { it1 -> MultipartBody.Part.createFormData("image",requestFile?.name, it1) }



            if (multipart != null) {
                Client.retrofitService.uploadImage(PreferenceManager.getLong(context,"PrefIDIndex"),binding?.contents?.text.toString(),binding?.title?.text.toString(),multipart)
                    .enqueue(object :
                        Callback<Void> {
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Log.d("에러네",t.toString())
                            Log.d("에러네", Uri.fromFile(requestFile).toString()
                            )
                        }

                        override fun onResponse(call: Call<Void>, response: Response<Void>) {


                            Toast.makeText(activity, "등록되었습니다.", Toast.LENGTH_SHORT).show()

                            (activity as MainActivity).setOnBackPressedListener(null)
                            childFragmentManager.popBackStackImmediate()

                            var parentFragment: FragmentManager =parentFragmentManager
                            parentFragment.beginTransaction()
                                .replace(R.id.boastContainer, BoastFragment())
                                .commit()
                        }

                    })
            }
            else Log.d("에러네","에러네")





        })


        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult( requestCode, resultCode, data );
        Log.d("제제",data?.data.toString())
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK)
        {
            cacheFilePath?.let { AlbumAdd(it) }
            // imageView.setImageBitmap(getBitmapCamera(imageView, cacheFilePath))
        }
        else if (requestCode == REQUEST_ALBUM && resultCode == RESULT_OK)
        {
            val albumUri = data?.getData()
            val fileName = getFileName(albumUri!!)
            try
            {
                val parcelFileDescriptor = activity?.applicationContext?.getContentResolver()?.openFileDescriptor(
                    albumUri!!, "r")
                if (parcelFileDescriptor == null) return
                val inputStream = FileInputStream(parcelFileDescriptor.getFileDescriptor())
                val cacheFile = File(activity?.applicationContext!!.getCacheDir(), fileName)
                val outputStream = FileOutputStream(cacheFile)
                IOUtils.copy(inputStream, outputStream)
                cacheFilePath = cacheFile.getAbsolutePath()
                file1=cacheFile
                //    imageView.setImageBitmap(getBitmapAlbum(imageView, albumUri))



            }
            catch (e:Exception) {
                e.printStackTrace()
            }
        }
        else if (requestCode === REQUEST_CAMERA && resultCode === RESULT_CANCELED)
        {
            fileDelete(cacheFilePath!!)
            cacheFilePath = null
        }



    }

    //캐시파일 생성
    fun createImgCacheFile():File {
        val cacheFile = File(activity?.applicationContext!!.cacheDir, SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date()) + ".jpg")
        return cacheFile
    }

    //카메라 호출
    fun onCamera(requestCode:Int, createTempFile:File) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(activity?.applicationContext!!.packageManager) != null)
        {
            if (createTempFile != null)
            {
                val photoURI = FileProvider.getUriForFile(activity?.applicationContext!!, BuildConfig.APPLICATION_ID, createTempFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(intent, requestCode)
            }
        }
    }


    //앨범 호출
    fun onAlbum(requestCode:Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE)
        startActivityForResult(intent, requestCode)
    }


    fun AlbumAdd(cacheFilePath:String) {

        if (cacheFilePath == null) return
        val options = BitmapFactory.Options()
        var exifInterface: ExifInterface? = null
        try
        {
            exifInterface = ExifInterface(cacheFilePath)
        }
        catch (e:Exception) {
            e.printStackTrace()
        }
        val exifOrientation:Int
        var exifDegree = 0
        if (exifInterface != null)
        {
            exifOrientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
            {
                exifDegree = 90
            }
            else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
            {

                exifDegree = 180

            }
            else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
            {
                exifDegree = 270
            }
        }
        val bitmap = BitmapFactory.decodeFile(cacheFilePath, options)
        val matrix = Matrix()
        matrix.postRotate(exifDegree.toFloat())
        val exifBit = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true)
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date()) + ".jpg")
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/*")
        values.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/AndroidQ")
        values.put(MediaStore.Images.Media.ORIENTATION, exifDegree)
        values.put(MediaStore.Images.Media.IS_PENDING, 1)
        val u = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        val uri = activity?.applicationContext!!.getContentResolver().insert(u, values)
        try
        {
            val parcelFileDescriptor = activity?.applicationContext!!.getContentResolver().openFileDescriptor(
                uri!!, "w", null)
            if (parcelFileDescriptor == null) return
            val byteArrayOutputStream = ByteArrayOutputStream()
            exifBit.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val b = byteArrayOutputStream.toByteArray()
            val inputStream = ByteArrayInputStream(b)
            val buffer = ByteArrayOutputStream()
            val bufferSize = 1024
            val buffers = ByteArray(bufferSize)
            var len = 0

            do{
                len = inputStream.read(buffers)
                buffer.write(buffers, 0, len)
            } while(len != -1)
            val bs = buffer.toByteArray()
            val fileOutputStream = FileOutputStream(parcelFileDescriptor.getFileDescriptor())
            fileOutputStream.write(bs)
            fileOutputStream.close()
            inputStream.close()
            parcelFileDescriptor.close()
            activity?.applicationContext!!.getContentResolver().update(uri, values, null, null)
        }
        catch (e:Exception) {
            e.printStackTrace()
        }
        values.clear()
        values.put(MediaStore.Images.Media.IS_PENDING, 0)
        activity?.applicationContext!!. getContentResolver().update(uri!!, values, null, null)
    }

    //이미지뷰에 뿌려질 앨범 비트맵 반환
    fun getBitmapAlbum(targetView:View, uri: Uri): Bitmap? {
        try
        {
            val parcelFileDescriptor = activity?.applicationContext!!.getContentResolver().openFileDescriptor(uri, "r")
            if (parcelFileDescriptor == null) return null
            val fileDescriptor = parcelFileDescriptor.getFileDescriptor()
            if (fileDescriptor == null) return null
            val targetW = targetView.getWidth()
            val targetH = targetView.getHeight()
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options)
            val photoW = options.outWidth
            val photoH = options.outHeight
            val scaleFactor = Math.min(photoW / targetW, photoH / targetH)
            if (scaleFactor >= 8)
            {
                options.inSampleSize = 8
            }
            else if (scaleFactor >= 4)
            {
                options.inSampleSize = 4
            }
            else
            {
                options.inSampleSize = 2
            }
            options.inJustDecodeBounds = false
            val reSizeBit = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options)
            var exifInterface: ExifInterface? = null
            try
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                {
                    exifInterface = ExifInterface(fileDescriptor)
                }
            }
            catch (e:IOException) {
                e.printStackTrace()
            }
            val exifOrientation:Int
            var exifDegree = 0
            //사진 회전값 구하기
            if (exifInterface != null)
            {
                exifOrientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
                if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
                {
                    exifDegree = 90
                }
                else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
                {
                    exifDegree = 180
                }
                else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
                {
                    exifDegree = 270
                }
            }
            parcelFileDescriptor.close()
            val matrix = Matrix()
            matrix.postRotate(exifDegree.toFloat())
            val reSizeExifBitmap = Bitmap.createBitmap(reSizeBit, 0, 0, reSizeBit.getWidth(), reSizeBit.getHeight(), matrix, true)
            return reSizeExifBitmap
        }
        catch (e:Exception) {

            e.printStackTrace()
            return null

        }

    }

    //이미지뷰에 뿌려질 카메라 비트맵 반환
    fun getBitmapCamera(targetView:View, filePath:String):Bitmap {
        val targetW = targetView.getWidth()
        val targetH = targetView.getHeight()
        // Get the dimensions of the bitmap
        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, bmOptions)
        val photoW = bmOptions.outWidth
        val photoH = bmOptions.outHeight
        val scaleFactor = Math.min(photoW / targetW, photoH / targetH).toDouble()
        if (scaleFactor >= 8)
        {
            bmOptions.inSampleSize = 8
        }
        else if (scaleFactor >= 4)
        {
            bmOptions.inSampleSize = 4
        }
        else
        {
            bmOptions.inSampleSize = 2
        }
        bmOptions.inJustDecodeBounds = false
        val originalBitmap = BitmapFactory.decodeFile(filePath, bmOptions)
        var exifInterface: ExifInterface? = null
        try
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                exifInterface = ExifInterface(filePath)
            }
        }
        catch (e:IOException) {
            e.printStackTrace()
        }
        val exifOrientation:Int
        var exifDegree = 0
        //사진 회전값 구하기
        if (exifInterface != null)
        {
            exifOrientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
            {
                exifDegree = 90
            }
            else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
            {
                exifDegree = 180
            }
            else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
            {
                exifDegree = 270
            }
        }
        val matrix = Matrix()
        matrix.postRotate(exifDegree.toFloat())
        val reSizeExifBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true)
        return reSizeExifBitmap
    }
    //앨범에서 선택한 사진이름 가져오기
    fun getFileName(uri:Uri): String? {

        Log.d("제발",activity?.applicationContext?.getContentResolver()?.query(uri, null, null, null, null).toString())
        val cursor = activity?.applicationContext?.getContentResolver()?.query(uri, null, null, null, null)
        try
        {
            if (cursor == null) return null
            cursor.moveToFirst()
            val fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            cursor.close()
            return fileName
        }
        catch (e:Exception) {
            e.printStackTrace()
            cursor!!.close()
            return null
        }
    }
    //파일삭제
    fun fileDelete(filePath:String) {
        if (filePath == null) return
        try
        {
            val f = File(filePath)
            if (f.exists())
            {
                f.delete()
            }
        }
        catch (e:Exception) {
            e.printStackTrace()
        }
    }

    //실제 앨범경로가 아닌 앱 내에 캐시디렉토리에 존재하는 이미지 캐시파일삭제
//확장자 .jpg 필터링해서 제거

    fun cacheDirFileClear() {
        val cacheDir = File(activity?.applicationContext!!.getCacheDir().getAbsolutePath())
        val cacheFiles = cacheDir.listFiles(object: FileFilter {
            override fun accept(pathname:File):Boolean {
                return pathname.getName().endsWith("jpg")
            }
        })
        if (cacheFiles == null) return
        for (c in cacheFiles)
        {
            fileDelete(c.getAbsolutePath())
        }
    }


    companion object {
        fun newInstance(): BoastChildFragment? {
            var fragment: BoastChildFragment = BoastChildFragment()
            return fragment
        }
    }


}