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

class MyAdapter4(val context: Context) : RecyclerView.Adapter<MyAdapter4.MyViewHolder4>() {
    //نام تیکست ویو ها
    private val nameList = arrayOf(
        "راهنمای مسیر",
        "راهنمای مسیر",
        "راهنمای مسیر",
        "راهنمای مسیر",
        "راهنمای مسیر",
        "راهنمای مسیر",
        "مهمانسرارا ۵۰۰ متر",
        "راهنمای مسیر",
        "راهنمای مسیر",
        "مهمانسرارا ۵۰۰ متر",
        "پارک سوار",
        "استراحتگاه ( چادر ) ۵۰۰ متر",
        "باز یا بسته بودن جاده کوهستانی",
        "محدودیت سرعت در کشور همسایه"
    )

    // نام ادرس تصویر
    private val imageUrls = arrayOf(
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7%DB%8C-%D9%85%D8%B3%DB%8C%D8%B1-3-247x196.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7%DB%8C-%D9%85%D8%B3%DB%8C%D8%B1-2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7%DB%8C-%D9%85%D8%B3%DB%8C%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7%DB%8C-%D9%85%D8%B3%DB%8C%D8%B1-6.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7%DB%8C-%D9%85%D8%B3%DB%8C%D8%B1-5.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7%DB%8C-%D9%85%D8%B3%DB%8C%D8%B1-4.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%85%D9%87%D9%85%D8%A7%D9%86%D8%B3%D8%B1%D8%A7-500-%D9%85%D8%AA%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7%DB%8C-%D9%85%D8%B3%DB%8C%D8%B1-8-247x160.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7%DB%8C-%D9%85%D8%B3%DB%8C%D8%B1-7-247x188.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%D8%B1%DA%A9-%D8%B3%D9%88%D8%A7%D8%B1-2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%D8%B1%DA%A9-%D8%B3%D9%88%D8%A7%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A7%D8%B3%D8%AA%D8%B1%D8%A7%D8%AD%D8%AA%DA%AF%D8%A7%D9%87-%DA%86%D8%A7%D8%AF%D8%B1-500-%D9%85%D8%AA%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A8%D8%A7%D8%B2-%DB%8C%D8%A7-%D8%A8%D8%B3%D8%AA%D9%87-%D8%A8%D9%88%D8%AF%D9%86-%D8%AC%D8%A7%D8%AF%D9%87-%DA%A9%D9%88%D9%87%D8%B3%D8%AA%D8%A7%D9%86%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%85%D8%AD%D8%AF%D9%88%D8%AF%DB%8C%D8%AA-%D8%B3%D8%B1%D8%B9%D8%AA-%D8%AF%D8%B1-%DA%A9%D8%B4%D9%88%D8%B1-%D9%87%D9%85%D8%B3%D8%A7%DB%8C%D9%87.png"
    )


    inner class MyViewHolder4(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val textView = itemView.findViewById<TextView>(R.id.textView3)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder4 {
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false)
        return MyViewHolder4(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder4, position: Int) {

        holder.textView.text = nameList[position]

        // استفاده از کتابخانه Glide برای بارگذاری تصاویر از اینترنت به ImageView
        Glide.with(context)
            .load(imageUrls[position])
//            .placeholder(R.drawable.placeholder_image) // تصویر پیشفرض در صورت بارگذاری تا زمان دریافت تصویر از اینترنت
//            .error(R.drawable.error_image) // تصویر پیشفرض در صورت بروز خطا در بارگذاری تصویر
            .into(holder.imageView)


    }
}
