package com.didimstory.mangulmangul;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.load.engine.Resource;

public class ZoomInPageTransformer(Resource resource) implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.85f; // scale // sizes

    private float imageMargin = resource.getDimensionPixelOffset(R.dimen.image_margin);
    private float imageSize = getResources().getDimensionPixelSize(R.dimen.image_size);
    private float screenWidth = getResources().getDisplayMetrics().widthPixels;
    private float offsetPx = screenWidth - imageMargin - imageSize;

    @Override
    public void transformPage(@NonNull View view, float position) {
        view.setTranslationX(position * -offsetPx);
        if (position < -1) return;
        if (position <= 1) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position * getEase(Math.abs(position))));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor); // If you use to LinearLayout in list_item, Use after codes.

            // int height = view.getHeight(); //float y = -((float) height - (scaleFactor * height ) ) / 4f;
            // view.setTranslationY(y);
        } else { // side views
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
        }
    }

    private float getEase(float position) {
        float sqt = position * position;
        return sqt / (2.0f * (sqt - position) + 1.0f);
    }


}


