package br.com.rms.gitconsult.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadDrawable(url: Int){

    val requestOptions = RequestOptions.fitCenterTransform()
    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.loadUrl(url: String){

    val requestOptions = RequestOptions.fitCenterTransform()
    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .into(this)

}