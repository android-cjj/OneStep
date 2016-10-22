package com.cjj.onestep;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by cjj on 2016/10/22.
 */

public class ViewBindingAdapter {

    @BindingAdapter({"imgUrl"})
    public static void showImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

}
