package br.com.pedrex.marvel.presentation.screens.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter

class BindingAdapter {
    companion object {

        @JvmStatic
        @BindingAdapter(value = ["url"], requireAll = false )
        fun loadImage(view: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide
                    .with(view.context)
                    .load(it)
                    .placeholder(android.R.drawable.picture_frame)
                    .into(view)
            }
        }
    }

}