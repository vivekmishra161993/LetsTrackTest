package com.app.lets_track_test.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.app.lets_track_test.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {
    @BindingAdapter(value = ["android:src","img_path"])
    @JvmStatic
    fun setImageUrl(imgView: AppCompatImageView, imgUrl: String?,imgPath:String?) {

        if (imgPath == null) {
            imgUrl?.let {
                val imgUri = it.toUri().buildUpon().scheme("https").build()
                Glide.with(imgView.context)
                        .load(imgUri)
                        .apply(
                                RequestOptions()
                                        .placeholder(R.drawable.newspaper)
                                        .error(R.drawable.newspaper))
                        .into(imgView)
            }
        }else{
            Glide.with(imgView.context).asBitmap().load(imgPath).apply(
                    RequestOptions()
                            .placeholder(R.drawable.newspaper)
                            .error(R.drawable.newspaper)).into(imgView);

        }
    }
}