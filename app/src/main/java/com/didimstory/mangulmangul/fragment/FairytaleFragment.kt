package com.didimstory.mangulmangul.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.viewpager2.widget.ViewPager2
import com.didimstory.mangulmangul.R
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FairytaleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FairytaleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    public var imageMargin:Int = 0
    public var imageSize:Int = 0
    public var screenWidth:Int= 0
    public var offsetPx:Int=0
/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

      imageMargin = getResources().getDimensionPixelOffset(R.dimen.image_margin)
          imageSize = getResources().getDimensionPixelSize(R.dimen.image_size)
            screenWidth = getResources().getDisplayMetrics().widthPixels
            offsetPx = screenWidth - imageMargin - imageSize

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
resources
        return inflater.inflate(R.layout.fragment_fairytale, container, false)
    }

    companion object {
        fun newInstance(): Int? {
            var fragment: FairytaleFragment = FairytaleFragment()
            Log.d("fairyfragment", fragment.toString())
            return 0
        }
    }

    private val imageList = ArrayList(
        Arrays.asList(
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Oryctolagus_cuniculus_Rcdo.jpg/220px-Oryctolagus_cuniculus_Rcdo.jpg",
            "https://dcist.com/wp-content/uploads/sites/3/2019/08/bunnies_web-768x512.jpg",
            "https://pec5c32suc11oospy1p2scl1-wpengine.netdna-ssl.com/wp-content/uploads/2018/02/rabbit-dental-tips-768x512.jpg",
            "https://www.thespruce.com/thmb/fA9UHj4cRsCDsAl2cHY_cDdcrBk=/960x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/controlling-and-deterring-rabbits-1402144-02-06514d1ca5b14c88b61ee9dc03dc4490.jpg",
            "https://static.independent.co.uk/s3fs-public/thumbnails/image/2019/01/07/11/bunny-rabbit.jpg?width=990"
        )
    )

    private class ZoomInPageTransformer( var imageMargin:Int,
            public var imageSize:Int,
            public var screenWidth:Int,
            public var offsetPx:Int,var getEase:Float): ViewPager2.PageTransformer {


       override fun transformPage(@NonNull view:View, position:Float) {
            view.setTranslationX(position * - offsetPx)
            if (position < -1) return
            if (position <= 1)
            {
                val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position * getEase(Math.abs(position))))
                view.setScaleX(scaleFactor)
                view.setScaleY(scaleFactor) // If you use to LinearLayout in list_item, Use after codes.
                run({ view.setScaleX(MIN_SCALE)
                    view.setScaleY(MIN_SCALE) })
            }//int height = view.getHeight();
            ////float y = -((float) height - (scaleFactor * height ) )
            // / 4f; //view.setTranslationY(y);
           }
            val getEase:Float
            ()
            val position:Float
            run({ val sqt = position * position
                return sqt / (2.0f * (sqt - position) + 1.0f) })
        }
        companion object {
            private val MIN_SCALE = 0.85f
        }
    }
    private fun getEase(position:Float):Float {
        val sqt = position * position
        return sqt / (2.0f * (sqt - position) + 1.0f)
    }*/
}