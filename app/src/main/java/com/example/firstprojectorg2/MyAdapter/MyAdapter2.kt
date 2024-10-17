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

class MyAdapter2(val context: Context) : RecyclerView.Adapter<MyAdapter2.MyViewHolder2>() {
    //نام تیکست ویو ها
    private val nameList = arrayOf(
        "شیب سرپائینی",
        "شیب سربالایی",
        "اولین پیچ به راست",
        "اولین پیچ به چپ",
        "سرازیری خطرناک",
        "سربالایی خطرناک",
        "پیچ به راست",
        "پیچ به چپ",
        "راه از راست و چپ باریک می شود",
        "راه از راست باریک می شود",
        "راه از چپ باریک می شود",
        "جهت وزش باد شدید از چپ",
        "خطر سقوط در آب",
        "دست انداز",
        "برآمدگی",
        "چاله",
        "راه لغزنده",
        "پرتاب سنگ",
        "ریزش سنگ",
        "پل متحرک",
        "گذرگاه عابر پیاده",
        "عبور عابر پیاده",
        "عبور عابر پیاده",
        "شانه خطرناک",
        "عبور حیوانات اهلی",
        "عبور دوچرخه سوار",
        "عبور اطفال",
        "عبور اطفال",
        "کارگران مشغول کارند",
        "عبور حیوانات وحشی",
        "عبور حیوانات وحشی",
        "عبور حیوانات اهلی",
        "تقاطع",
        "تقاطع",
        "تقاطع",
        "چراغ راهنما",
        "تقاطع",
        "تقاطع",
        "تقاطع",
        "تقاطع",
        "ورود به راه اصلی از راست",
        "تقاطع فرعی و اصلی",
        "تقاطع",
        "تقاطع",
        "تقاطع راه آهن با راهبند",
        "ورود به راه اصلی از راست",
        "ورود به راه اصلی از چپ ",
        "ورود به راه اصلی از چپ",
        "تقاطع مسیر قطار شهری",
        "تقاطع با راه آهن",
        "به تابلو رعایت حق تقدم نزدیک می شوید",
        "تراکم ترافیک",
        "جاده دو طرفه",
        "میدان",
        "خطر",
        "تقاطع راه آهن بدون راهبند",
        "پرواز هواپیما در ارتفاع کم",
        "پرواز هواپیما در ارتفاع کم",
        "تقاطع جاده و راه آهن بدون راهبند نصب در ۳۰۰ متری",
        "تقاطع جاده و راه آهن با راهبند نصب در ۳۰۰ متری",
        "جاده دو طرفه",
        "تقاطع جاده و راه آهن در ۱۰۰ متری",
        "تقاطع جاده و راه آهن در ۲۰۰ متری",
        "تقاطع جاده و راه آهن در ۳۰۰ متری",


        )

    // نام ادرس تصویر
    private val imageUrls = arrayOf(

        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B4%DB%8C%D8%A8-%D8%B3%D8%B1%D9%BE%D8%A7%D8%A6%DB%8C%D9%86%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B4%DB%8C%D8%A8-%D8%B3%D8%B1%D8%A8%D8%A7%D9%84%D8%A7%DB%8C%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A7%D9%88%D9%84%DB%8C%D9%86-%D9%BE%DB%8C%DA%86-%D8%A8%D9%87-%D8%B1%D8%A7%D8%B3%D8%AA.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A7%D9%88%D9%84%DB%8C%D9%86-%D9%BE%DB%8C%DA%86-%D8%A8%D9%87-%DA%86%D9%BE.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B3%D8%B1%D8%A7%D8%B2%DB%8C%D8%B1%DB%8C-%D8%AE%D8%B7%D8%B1%D9%86%D8%A7%DA%A9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B3%D8%B1%D8%A8%D8%A7%D9%84%D8%A7%DB%8C%DB%8C-%D8%AE%D8%B7%D8%B1%D9%86%D8%A7%DA%A9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%DB%8C%DA%86-%D8%A8%D9%87-%D8%B1%D8%A7%D8%B3%D8%AA.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%DB%8C%DA%86-%D8%A8%D9%87-%DA%86%D9%BE.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87-%D8%A7%D8%B2-%D8%B1%D8%A7%D8%B3%D8%AA-%D9%88-%DA%86%D9%BE-%D8%A8%D8%A7%D8%B1%DB%8C%DA%A9-%D9%85%DB%8C-%D8%B4%D9%88%D8%AF.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87-%D8%A7%D8%B2-%D8%B1%D8%A7%D8%B3%D8%AA-%D8%A8%D8%A7%D8%B1%DB%8C%DA%A9-%D9%85%DB%8C-%D8%B4%D9%88%D8%AF.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87-%D8%A7%D8%B2-%DA%86%D9%BE-%D8%A8%D8%A7%D8%B1%DB%8C%DA%A9-%D9%85%DB%8C-%D8%B4%D9%88%D8%AF.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AC%D9%87%D8%AA-%D9%88%D8%B2%D8%B4-%D8%A8%D8%A7%D8%AF-%D8%B4%D8%AF%DB%8C%D8%AF-%D8%A7%D8%B2-%DA%86%D9%BE.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AE%D8%B7%D8%B1-%D8%B3%D9%82%D9%88%D8%B7-%D8%AF%D8%B1-%D8%A2%D8%A8.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AF%D8%B3%D8%AA-%D8%A7%D9%86%D8%AF%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%A8%D8%B1%D8%A2%D9%85%D8%AF%DA%AF%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%DA%86%D8%A7%D9%84%D9%87.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87-%D9%84%D8%BA%D8%B2%D9%86%D8%AF%D9%87.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%B1%D8%AA%D8%A7%D9%BE-%D8%B3%D9%86%DA%AF.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%DB%8C%D8%B2%D8%B4-%D8%B3%D9%86%DA%AF.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D9%84-%D9%85%D8%AA%D8%AD%D8%B1%DA%A9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%DA%AF%D8%B0%D8%B1%DA%AF%D8%A7%D9%87-%D8%B9%D8%A7%D8%A8%D8%B1-%D9%BE%DB%8C%D8%A7%D8%AF%D9%87.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%B9%D8%A7%D8%A8%D8%B1-%D9%BE%DB%8C%D8%A7%D8%AF%D9%872.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%B9%D8%A7%D8%A8%D8%B1-%D9%BE%DB%8C%D8%A7%D8%AF%D9%87.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B4%D8%A7%D9%86%D9%87-%D8%AE%D8%B7%D8%B1%D9%86%D8%A7%DA%A9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%AD%DB%8C%D9%88%D8%A7%D9%86%D8%A7%D8%AA-%D8%A7%D9%87%D9%84%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%AF%D9%88%DA%86%D8%B1%D8%AE%D9%87-%D8%B3%D9%88%D8%A7%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%A7%D8%B7%D9%81%D8%A7%D9%84-2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%A7%D8%B7%D9%81%D8%A7%D9%84.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%DA%A9%D8%A7%D8%B1%DA%AF%D8%B1%D8%A7%D9%86-%D9%85%D8%B4%D8%BA%D9%88%D9%84-%DA%A9%D8%A7%D8%B1%D9%86%D8%AF.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%AD%DB%8C%D9%88%D8%A7%D9%86%D8%A7%D8%AA-%D9%88%D8%AD%D8%B4%DB%8C-2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%AD%DB%8C%D9%88%D8%A7%D9%86%D8%A7%D8%AA-%D9%88%D8%AD%D8%B4%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%AD%DB%8C%D9%88%D8%A7%D9%86%D8%A7%D8%AA-%D8%A7%D9%87%D9%84%DB%8C-2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%DA%86%D8%B1%D8%A7%D8%BA-%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-6.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-5.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-4.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-3.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%88%D8%B1%D9%88%D8%AF-%D8%A8%D9%87-%D8%B1%D8%A7%D9%87-%D8%A7%D8%B5%D9%84%DB%8C-%D8%A7%D8%B2-%D8%B1%D8%A7%D8%B3%D8%AA.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D9%81%D8%B1%D8%B9%DB%8C-%D9%88-%D8%A7%D8%B5%D9%84%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-8.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-7.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D8%B1%D8%A7%D9%87-%D8%A2%D9%87%D9%86-%D8%A8%D8%A7-%D8%B1%D8%A7%D9%87%D8%A8%D9%86%D8%AF.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%88%D8%B1%D9%88%D8%AF-%D8%A8%D9%87-%D8%B1%D8%A7%D9%87-%D8%A7%D8%B5%D9%84%DB%8C-%D8%A7%D8%B2-%D8%B1%D8%A7%D8%B3%D8%AA-2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%88%D8%B1%D9%88%D8%AF-%D8%A8%D9%87-%D8%B1%D8%A7%D9%87-%D8%A7%D8%B5%D9%84%DB%8C-%D8%A7%D8%B2-%DA%86%D9%BE-2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%88%D8%B1%D9%88%D8%AF-%D8%A8%D9%87-%D8%B1%D8%A7%D9%87-%D8%A7%D8%B5%D9%84%DB%8C-%D8%A7%D8%B2-%DA%86%D9%BE.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D9%85%D8%B3%DB%8C%D8%B1-%D9%82%D8%B7%D8%A7%D8%B1-%D8%B4%D9%87%D8%B1%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D8%A8%D8%A7-%D8%B1%D8%A7%D9%87-%D8%A2%D9%87%D9%86.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%A8%D9%87-%D8%AA%D8%A7%D8%A8%D9%84%D9%88-%D8%B1%D8%B9%D8%A7%DB%8C%D8%AA-%D8%AD%D9%82-%D8%AA%D9%82%D8%AF%D9%85-%D9%86%D8%B2%D8%AF%DB%8C%DA%A9-%D9%85%DB%8C-%D8%B4%D9%88%DB%8C%D8%AF.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D8%B1%D8%A7%DA%A9%D9%85-%D8%AA%D8%B1%D8%A7%D9%81%DB%8C%DA%A9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AC%D8%A7%D8%AF%D9%87-%D8%AF%D9%88-%D8%B7%D8%B1%D9%81%D9%87.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%85%DB%8C%D8%AF%D8%A7%D9%86.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AE%D8%B7%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D8%B1%D8%A7%D9%87-%D8%A2%D9%87%D9%86-%D8%A8%D8%AF%D9%88%D9%86-%D8%B1%D8%A7%D9%87%D8%A8%D9%86%D8%AF.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%BE%D8%B1%D9%88%D8%A7%D8%B2-%D9%87%D9%88%D8%A7%D9%BE%DB%8C%D9%85%D8%A7-%D8%AF%D8%B1-%D8%A7%D8%B1%D8%AA%D9%81%D8%A7%D8%B9-%DA%A9%D9%85.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%BE%D8%B1%D9%88%D8%A7%D8%B2-%D9%87%D9%88%D8%A7%D9%BE%DB%8C%D9%85%D8%A7-%D8%AF%D8%B1-%D8%A7%D8%B1%D8%AA%D9%81%D8%A7%D8%B9-%DA%A9%D9%85-2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D8%AC%D8%A7%D8%AF%D9%87-%D9%88-%D8%B1%D8%A7%D9%87-%D8%A2%D9%87%D9%86-%D8%A8%D8%AF%D9%88%D9%86-%D8%B1%D8%A7%D9%87%D8%A8%D9%86%D8%AF-%D9%86%D8%B5%D8%A8-%D8%AF%D8%B1-300-%D9%85%D8%AA%D8%B1%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D8%AC%D8%A7%D8%AF%D9%87-%D9%88-%D8%B1%D8%A7%D9%87-%D8%A2%D9%87%D9%86-%D8%A8%D8%A7-%D8%B1%D8%A7%D9%87%D8%A8%D9%86%D8%AF-%D9%86%D8%B5%D8%A8-%D8%AF%D8%B1-300-%D9%85%D8%AA%D8%B1%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D8%A8%D8%A7-%D8%B1%D8%A7%D9%87-%D8%A2%D9%87%D9%86-%D8%AF%D9%88-%D8%AE%D8%B7%D9%87.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D8%AC%D8%A7%D8%AF%D9%87-%D9%88-%D8%B1%D8%A7%D9%87-%D8%A2%D9%87%D9%86-%D8%AF%D8%B1-100-%D9%85%D8%AA%D8%B1%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D8%AC%D8%A7%D8%AF%D9%87-%D9%88-%D8%B1%D8%A7%D9%87-%D8%A2%D9%87%D9%86-%D8%AF%D8%B1-200-%D9%85%D8%AA%D8%B1%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%82%D8%A7%D8%B7%D8%B9-%D8%AC%D8%A7%D8%AF%D9%87-%D9%88-%D8%B1%D8%A7%D9%87-%D8%A2%D9%87%D9%86-%D8%AF%D8%B1-300-%D9%85%D8%AA%D8%B1%DB%8C.png",
    )


    inner class MyViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val textView = itemView.findViewById<TextView>(R.id.textView3)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false)
        return MyViewHolder2(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {

        holder.textView.text = nameList[position]

        // استفاده از کتابخانه Glide برای بارگذاری تصاویر از اینترنت به ImageView
        Glide.with(context)
            .load(imageUrls[position])
//            .placeholder(R.drawable.placeholder_image) // تصویر پیشفرض در صورت بارگذاری تا زمان دریافت تصویر از اینترنت
//            .error(R.drawable.error_image) // تصویر پیشفرض در صورت بروز خطا در بارگذاری تصویر
            .into(holder.imageView)


    }
}