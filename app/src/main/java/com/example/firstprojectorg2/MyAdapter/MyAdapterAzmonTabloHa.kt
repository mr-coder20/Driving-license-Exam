package com.example.firstprojectorg2.MyAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.firstprojectorg2.Azmon_tabloHa.*
import com.example.firstprojectorg2.R
import jp.wasabeef.glide.transformations.CropCircleTransformation

class MyAdapterAzmonTabloHa(
    private val context: Context,
    private val imageUrls: List<String>
) : RecyclerView.Adapter<MyAdapterAzmonTabloHa.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageAzmonTabloHa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_azmon_tablo_ha, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Configure Glide for fast loading
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache all versions of the image
            .skipMemoryCache(false) // Enable memory cache
            .override(128, 128) // Resize image

        // Load image with Glide
        Glide.with(context)
            .load(imageUrls[position])
            .apply(requestOptions)
            .apply(RequestOptions.bitmapTransform(CropCircleTransformation()))
            .into(holder.imageView)

        // Set click listener
        holder.itemView.setOnClickListener {
            val intent = when (position) {
                0 -> Intent(context, AzmonT1::class.java)
                1 -> Intent(context, AzmonT2::class.java)
                2 -> Intent(context, AzmonT3::class.java)
                3 -> Intent(context, AzmonT4::class.java)
                4 -> Intent(context, AzmonT5::class.java)
                5 -> Intent(context, AzmonT6::class.java)
                6 -> Intent(context, AzmonT7::class.java)
                7 -> Intent(context, AzmonT8::class.java)
                else -> null
            }
            intent?.let { context.startActivity(it) }
        }
    }

    override fun getItemCount() = imageUrls.size
}
