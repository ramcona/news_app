package id.rafli.baseproject.extention

import android.widget.ImageView
import com.bumptech.glide.Glide
import id.rafli.baseproject.helper.Config.BASE_URL

fun ImageView.loadImage(path: String) {
    val url = BASE_URL + path
    Glide.with(context)
        .load(url)
        .into(this)
}