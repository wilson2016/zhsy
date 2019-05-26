package com.zhsy.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zhsy.R;


public class ImageUtils {


    public static void loadImage(Context context, String imageURL, ImageView view){

        Glide.with(context).setDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_loading)
                .override(DisplayUtil.getScreenWidthInPx(context)))
                .load(imageURL).into(view);
    }

}
