package com.example.firstprojectorg2.MyAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstprojectorg2.R

class MyAdapter(val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val nameList = arrayOf(
        "پایان شهر",
        "پایان منطقه مسکونی",
        "ابتدای منطقه مسکونی",
        "ورود به شهر",
        "پایان آزاد راه",
        "آزاد راه",
        "پایان منطقه محدودیت سرعت",
        "منطقه محدودیت سرعت",
        "پایان محدوده توقف ممنوع",
        "محدوده توقف ممنوع در طول شبانه روز",
        "پایان محدوده توقف ممنوع در ساعات معین",
        "محدوده توقف ممنوع در ساعات معین",
        "تعمیرگاه",
        "تلفن عمومی",
        "جایگاه سوخت",
        "هتل",
        "پارک سوار ",
        "پست امدادی ",
        "ایستگاه قطار شهری",
        "ایستگاه اتوبوس",
        "بیمارستان",
        "غذاخوری",
        "پارکینگ",
        "پارک سوار",
        "محدوده پارک آزاد",
        "تونل",
        "پایان تونل",
        "گذرگاه پیاده",
    )
    private val imageUrls = arrayOf(
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D8%B4%D9%87%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D9%85%D9%86%D8%B7%D9%82%D9%87-%D9%85%D8%B3%DA%A9%D9%88%D9%86%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A7%D8%A8%D8%AA%D8%AF%D8%A7%DB%8C-%D9%85%D9%86%D8%B7%D9%82%D9%87-%D9%85%D8%B3%DA%A9%D9%88%D9%86%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%88%D8%B1%D9%88%D8%AF-%D8%A8%D9%87-%D8%B4%D9%87%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D8%A2%D8%B2%D8%A7%D8%AF-%D8%B1%D8%A7%D9%87.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A2%D8%B2%D8%A7%D8%AF-%D8%B1%D8%A7%D9%87.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D9%85%D9%86%D8%B7%D9%82%D9%87-%D9%85%D8%AD%D8%AF%D9%88%D8%AF%DB%8C%D8%AA-%D8%B3%D8%B1%D8%B9%D8%AA.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%85%D9%86%D8%B7%D9%82%D9%87-%D9%85%D8%AD%D8%AF%D9%88%D8%AF%DB%8C%D8%AA-%D8%B3%D8%B1%D8%B9%D8%AA.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D9%85%D8%AD%D8%AF%D9%88%D8%AF%D9%87-%D8%AA%D9%88%D9%82%D9%81-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%85%D8%AD%D8%AF%D9%88%D8%AF%D9%87-%D8%AA%D9%88%D9%82%D9%81-%D9%85%D9%85%D9%86%D9%88%D8%B9-%D8%AF%D8%B1-%D8%B7%D9%88%D9%84-%D8%B4%D8%A8%D8%A7%D9%86%D9%87-%D8%B1%D9%88%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D9%85%D8%AD%D8%AF%D9%88%D8%AF%D9%87-%D8%AA%D9%88%D9%82%D9%81-%D9%85%D9%85%D9%86%D9%88%D8%B9-%D8%AF%D8%B1-%D8%B3%D8%A7%D8%B9%D8%A7%D8%AA-%D9%85%D8%B9%DB%8C%D9%86.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%85%D8%AD%D8%AF%D9%88%D8%AF%D9%87-%D8%AA%D9%88%D9%82%D9%81-%D9%85%D9%85%D9%86%D9%88%D8%B9-%D8%AF%D8%B1-%D8%B3%D8%A7%D8%B9%D8%A7%D8%AA-%D9%85%D8%B9%DB%8C%D9%86.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D8%B9%D9%85%DB%8C%D8%B1%DA%AF%D8%A7%D9%87.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%84%D9%81%D9%86-%D8%B9%D9%85%D9%88%D9%85%DB%8C.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AC%D8%A7%DB%8C%DA%AF%D8%A7%D9%87-%D8%B3%D9%88%D8%AE%D8%AA.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%87%D8%AA%D9%84.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%D8%B1%DA%A9-%D8%B3%D9%88%D8%A7%D8%B1-2.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%B3%D8%AA-%D8%A7%D9%85%D8%AF%D8%A7%D8%AF%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A7%DB%8C%D8%B3%D8%AA%DA%AF%D8%A7%D9%87-%D9%82%D8%B7%D8%A7%D8%B1-%D8%B4%D9%87%D8%B1%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A7%DB%8C%D8%B3%D8%AA%DA%AF%D8%A7%D9%87-%D8%A7%D8%AA%D9%88%D8%A8%D9%88%D8%B3.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A8%DB%8C%D9%85%D8%A7%D8%B1%D8%B3%D8%AA%D8%A7%D9%86.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%BA%D8%B0%D8%A7%D8%AE%D9%88%D8%B1%DB%8C.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%D8%B1%DA%A9%DB%8C%D9%86%DA%AF.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%D8%B1%DA%A9-%D8%B3%D9%88%D8%A7%D8%B1.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%85%D8%AD%D8%AF%D9%88%D8%AF%D9%87-%D9%BE%D8%A7%D8%B1%DA%A9-%D8%A2%D8%B2%D8%A7%D8%AF.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%88%D9%86%D9%84.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D8%AA%D9%88%D9%86%D9%84.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%DA%AF%D8%B0%D8%B1%DA%AF%D8%A7%D9%87-%D9%BE%DB%8C%D8%A7%D8%AF%D9%87.png",
    )


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView = itemView.findViewById<TextView>(R.id.textView3)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = nameList[position]

        // استفاده از کتابخانه Glide برای بارگذاری تصاویر از اینترنت به ImageView
        Glide.with(context)
            .load(imageUrls[position])
//            .placeholder(R.drawable.placeholder_image) // تصویر پیشفرض در صورت بارگذاری تا زمان دریافت تصویر از اینترنت
//            .error(R.drawable.error_image) // تصویر پیشفرض در صورت بروز خطا در بارگذاری تصویر
            .into(holder.imageView)
    }

}