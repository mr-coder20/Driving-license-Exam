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

class MyAdapter3(val context: Context) : RecyclerView.Adapter<MyAdapter3.MyViewHolder3>() {
    //نام تیکست ویو ها
    private val nameList = arrayOf(

        "عبور سواری ممنوع",
        "ورود از هر دو طرف ممنوع",
        "ورود ممنوع",
        "حق تقدم عبور با شما",
        "رعایت حق تقدم",
        "حق تقدم عبور با وسیله نقلیه مقابل است",
        "عبور وسائط نقلیه با وزن بیش از ۵٫۵ تن ممنوع",
        "ایست",
        "عبور دوچرخه ممنوع",
        "عبور موتور سیکلت ممنوع",
        "پایان خیابان اصلی",
        "خیابان اصلی",
        "عبور عابر پیاده ممنوع",
        "عبور چرخ دستی ممنوع",
        "عبور کامیون یدک دار ممنوع",
        "عبور کامیون ممنوع",
        "عبور کلیه وسایل نقلیه ممنوع",
        "عبور وسایط نقلیه موتوری ممنوع",
        "عبور گاری ممنوع",
        "عبور خودرو با یدک ممنوع",
        "عبور وسایل نقلیه با محموله خطرناک ممنوع",
        "عبور با ارتفاع بیش از ۲٫۹ متر ممنوع",
        "عبور با عرض بیش از ۲ متر ممنوع",
        "عبور با بار بیش از ۲٫۴ تن ممنوع",
        "رعایت فاصله کمتر از ۵۰ متر ممنوع",
        "عبور کامیون با طول بیش از ۱۰ متر ممنوع",
        "گردش به راست ممنوع",
        "گردش به چپ ممنوع",
        "سبقت برای کامیون ممنوع",
        "سبقت ممنوع",
        "سرعت بیش از ۳۰ کیلومتر بر ساعت ممنوع",
        "دور زدن ممنوع",
        "پایان تمام محدودیتها",
        "پایان سبقت ممنوع برای کامیون",
        "پایان سبقت ممنوع",
        "ایست بازرسی (گمرک",
        "منطقه توقف ممنوع",
        "پایان محدودیت سرعت ۸۰ کیلومتر",
        "بوق زدن ممنوع",
        "توقف ممنوع",
        "ایستادن ممنوع",
        "پایان منطقه توقف ممنوع",
        "توقف در روزهای فرد ممنوع",
        "توقف در روزهای زوج هفته ممنوع",
        "عبور از سمت راست مجاز" ,
        "فقط عبور مستقیم مجاز" ,
        "فقط عبور راست مجاز" ,
        "فقط عبور به چپ مجاز" ,
        "فقط گردش به راست مجاز" ,
        "فقط گردش به چپ مجاز" ,
        "عبور مستقیم و گردش به چپ مجاز" ,
        "عبور مستقیم و گردش به راست مجاز" ,
        "فقط عبور عابر پیاده" ,
        "عبور از هر دو سمت مجاز" ,
        "جهت عبور در میدان" ,
        "عبور از سمت چپ مجاز" ,
        "پایان حداقل سرعت ۳۰ کیلومتر" ,
        "حداقل سرعت ۳۰ کیلومتر" ,
        "فقط عبور اسب سوار" ,
        "فقط عبور دوچرخه" ,
        "راه یک طرفه" ,
        "خیابان یک طرفه" ,
        "حداقل سرعت در خط های عبور" ,
        "حداکثر سرعت در خط های عبور" ,
        "مسیر کامیون حامل کالای خطرناک" ,
        "عبور فقط با زنجیر چرخ" ,
        "فقط عبور دوچرخه و پیاده" ,
        "خط ویژه عبور" ,
    )

    // نام ادرس تصویر
    private val imageUrls = arrayOf(
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%B3%D9%88%D8%A7%D8%B1%DB%8C-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%88%D8%B1%D9%88%D8%AF-%D8%A7%D8%B2-%D9%87%D8%B1-%D8%AF%D9%88-%D8%B7%D8%B1%D9%81-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%88%D8%B1%D9%88%D8%AF-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AD%D9%82-%D8%AA%D9%82%D8%AF%D9%85-%D8%B9%D8%A8%D9%88%D8%B1-%D8%A8%D8%A7-%D8%B4%D9%85%D8%A7.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B1%D8%B9%D8%A7%DB%8C%D8%AA-%D8%AD%D9%82-%D8%AA%D9%82%D8%AF%D9%85.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AD%D9%82-%D8%AA%D9%82%D8%AF%D9%85-%D8%B9%D8%A8%D9%88%D8%B1-%D8%A8%D8%A7-%D9%88%D8%B3%DB%8C%D9%84%D9%87-%D9%86%D9%82%D9%84%DB%8C%D9%87-%D9%85%D9%82%D8%A7%D8%A8%D9%84-%D8%A7%D8%B3%D8%AA.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B9%D8%A8%D9%88%D8%B1-%D9%88%D8%B3%D8%A7%D8%A6%D8%B7-%D9%86%D9%82%D9%84%DB%8C%D9%87-%D8%A8%D8%A7-%D9%88%D8%B2%D9%86-%D8%A8%DB%8C%D8%B4-%D8%A7%D8%B2-5.5-%D8%AA%D9%86-%D9%85%D9%85%D9%86%D9%88%D8%B9.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%A7%DB%8C%D8%B3%D8%AA.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%AF%D9%88%DA%86%D8%B1%D8%AE%D9%87-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D9%85%D9%88%D8%AA%D9%88%D8%B1-%D8%B3%DB%8C%DA%A9%D9%84%D8%AA-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D8%AE%DB%8C%D8%A7%D8%A8%D8%A7%D9%86-%D8%A7%D8%B5%D9%84%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%AE%DB%8C%D8%A7%D8%A8%D8%A7%D9%86-%D8%A7%D8%B5%D9%84%DB%8C.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%B9%D8%A7%D8%A8%D8%B1-%D9%BE%DB%8C%D8%A7%D8%AF%D9%87-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%DA%86%D8%B1%D8%AE-%D8%AF%D8%B3%D8%AA%DB%8C-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%DA%A9%D8%A7%D9%85%DB%8C%D9%88%D9%86-%DB%8C%D8%AF%DA%A9-%D8%AF%D8%A7%D8%B1-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%DA%A9%D8%A7%D9%85%DB%8C%D9%88%D9%86-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%DA%A9%D9%84%DB%8C%D9%87-%D9%88%D8%B3%D8%A7%DB%8C%D8%B7-%D9%86%D9%82%D9%84%DB%8C%D9%87-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D9%88%D8%B3%D8%A7%DB%8C%D8%B7-%D9%86%D9%82%D9%84%DB%8C%D9%87-%D9%85%D9%88%D8%AA%D9%88%D8%B1%DB%8C-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%DA%AF%D8%A7%D8%B1%DB%8C-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%AE%D9%88%D8%AF%D8%B1%D9%88-%D8%A8%D8%A7-%DB%8C%D8%AF%DA%A9-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D9%88%D8%B3%D8%A7%DB%8C%D8%B7-%D9%86%D9%82%D9%84%DB%8C%D9%87-%D8%A8%D8%A7-%D9%85%D8%AD%D9%85%D9%88%D9%84%D9%87-%D8%AE%D8%B7%D8%B1%D9%86%D8%A7%DA%A9-%D9%85%D9%85%D9%86%D9%88%D8%B9.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%A8%D8%A7-%D8%A7%D8%B1%D8%AA%D9%81%D8%A7%D8%B9-%D8%A8%DB%8C%D8%B4-%D8%A7%D8%B2-2.9-%D9%85%D8%AA%D8%B1-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%A8%D8%A7-%D8%B9%D8%B1%D8%B6-%D8%A8%DB%8C%D8%B4-%D8%A7%D8%B2-2-%D9%85%D8%AA%D8%B1-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B9%D8%A8%D9%88%D8%B1-%D8%A8%D8%A7-%D8%A8%D8%A7%D8%B1-%D8%A8%DB%8C%D8%B4-%D8%A7%D8%B2-2.4-%D8%AA%D9%86-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B1%D8%B9%D8%A7%DB%8C%D8%AA-%D9%81%D8%A7%D8%B5%D9%84%D9%87-%DA%A9%D9%85%D8%AA%D8%B1-%D8%A7%D8%B2-50-%D9%85%D8%AA%D8%B1-%D9%85%D9%85%D9%86%D9%88%D8%B9.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B9%D8%A8%D9%88%D8%B1-%DA%A9%D8%A7%D9%85%DB%8C%D9%88%D9%86-%D8%A8%D8%A7-%D8%B7%D9%88%D9%84-%D8%A8%DB%8C%D8%B4-%D8%A7%D8%B2-10-%D9%85%D8%AA%D8%B1-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%DA%AF%D8%B1%D8%AF%D8%B4-%D8%A8%D9%87-%D8%B1%D8%A7%D8%B3%D8%AA-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%DA%AF%D8%B1%D8%AF%D8%B4-%D8%A8%D9%87-%DA%86%D9%BE-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B3%D8%A8%D9%82%D8%AA-%D8%A8%D8%B1%D8%A7%DB%8C-%DA%A9%D8%A7%D9%85%DB%8C%D9%88%D9%86-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B3%D8%A8%D9%82%D8%AA-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B3%D8%B1%D8%B9%D8%AA-%D8%A8%DB%8C%D8%B4-%D8%A7%D8%B2-30-%DA%A9%DB%8C%D9%84%D9%88%D9%85%D8%AA%D8%B1-%D8%A8%D8%B1-%D8%B3%D8%A7%D8%B9%D8%AA-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AF%D9%88%D8%B1-%D8%B2%D8%AF%D9%86-%D9%85%D9%85%D9%86%D9%88%D8%B9.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D8%AA%D9%85%D8%A7%D9%85-%D9%85%D8%AD%D8%AF%D9%88%D8%AF%DB%8C%D8%AA-%D9%87%D8%A7.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D8%B3%D8%A8%D9%82%D8%AA-%D9%85%D9%85%D9%86%D9%88%D8%B9-%D8%A8%D8%B1%D8%A7%DB%8C-%DA%A9%D8%A7%D9%85%DB%8C%D9%88%D9%86.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D8%B3%D8%A8%D9%82%D8%AA-%D9%85%D9%85%D9%86%D9%88%D8%B9.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%A7%DB%8C%D8%B3%D8%AA-%D8%A8%D8%A7%D8%B2%D8%B1%D8%B3%DB%8C-%DA%AF%D9%85%D8%B1%DA%A9-.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%85%D9%86%D8%B7%D9%82%D9%87-%D8%AA%D9%88%D9%82%D9%81-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D9%85%D8%AD%D8%AF%D9%88%D8%AF%DB%8C%D8%AA-%D8%B3%D8%B1%D8%B9%D8%AA-80-%DA%A9%DB%8C%D9%84%D9%88%D9%85%D8%AA%D8%B1.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%A8%D9%88%D9%82-%D8%B2%D8%AF%D9%86-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%88%D9%82%D9%81-%D9%85%D9%85%D9%86%D9%88%D8%B9.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%A7%DB%8C%D8%B3%D8%AA%D8%A7%D8%AF%D9%86-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D9%85%D9%86%D8%B7%D9%82%D9%87-%D8%AA%D9%88%D9%82%D9%81-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%88%D9%82%D9%81-%D8%AF%D8%B1-%D8%B1%D9%88%D8%B2%D9%87%D8%A7%DB%8C-%D9%81%D8%B1%D8%AF-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AA%D9%88%D9%82%D9%81-%D8%AF%D8%B1-%D8%B1%D9%88%D8%B2%D9%87%D8%A7%DB%8C-%D8%B2%D9%88%D8%AC-%D9%87%D9%81%D8%AA%D9%87-%D9%85%D9%85%D9%86%D9%88%D8%B9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B9%D8%A8%D9%88%D8%B1-%D8%A7%D8%B2-%D8%B3%D9%85%D8%AA-%D8%B1%D8%A7%D8%B3%D8%AA-%D9%85%D8%AC%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%81%D9%82%D8%B7-%D8%B9%D8%A8%D9%88%D8%B1-%D9%85%D8%B3%D8%AA%D9%82%DB%8C%D9%85-%D9%85%D8%AC%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%81%D9%82%D8%B7-%D8%B9%D8%A8%D9%88%D8%B1-%D8%B1%D8%A7%D8%B3%D8%AA-%D9%85%D8%AC%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%81%D9%82%D8%B7-%D8%B9%D8%A8%D9%88%D8%B1-%D8%A8%D9%87-%DA%86%D9%BE-%D9%85%D8%AC%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%81%D9%82%D8%B7-%DA%AF%D8%B1%D8%AF%D8%B4-%D8%A8%D9%87-%D8%B1%D8%A7%D8%B3%D8%AA-%D9%85%D8%AC%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%81%D9%82%D8%B7-%DA%AF%D8%B1%D8%AF%D8%B4-%D8%A8%D9%87-%DA%86%D9%BE-%D9%85%D8%AC%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B9%D8%A8%D9%88%D8%B1-%D9%85%D8%B3%D8%AA%D9%82%DB%8C%D9%85-%D9%88-%DA%AF%D8%B1%D8%AF%D8%B4-%D8%A8%D9%87-%DA%86%D9%BE-%D9%85%D8%AC%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B9%D8%A8%D9%88%D8%B1-%D9%85%D8%B3%D8%AA%D9%82%DB%8C%D9%85-%D9%88-%DA%AF%D8%B1%D8%AF%D8%B4-%D8%A8%D9%87-%D8%B1%D8%A7%D8%B3%D8%AA-%D9%85%D8%AC%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%81%D9%82%D8%B7-%D8%B9%D8%A8%D9%88%D8%B1-%D8%B9%D8%A7%D8%A8%D8%B1-%D9%BE%DB%8C%D8%A7%D8%AF%D9%87.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B9%D8%A8%D9%88%D8%B1-%D8%A7%D8%B2-%D9%87%D8%B1-%D8%AF%D9%88-%D8%B3%D9%85%D8%AA-%D9%85%D8%AC%D8%A7%D8%B2.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AC%D9%87%D8%AA-%D8%B9%D8%A8%D9%88%D8%B1-%D8%AF%D8%B1-%D9%85%DB%8C%D8%AF%D8%A7%D9%86.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B9%D8%A8%D9%88%D8%B1-%D8%A7%D8%B2-%D8%B3%D9%85%D8%AA-%DA%86%D9%BE-%D9%85%D8%AC%D8%A7%D8%B2.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%BE%D8%A7%DB%8C%D8%A7%D9%86-%D8%AD%D8%AF%D8%A7%D9%82%D9%84-%D8%B3%D8%B1%D8%B9%D8%AA-30-%DA%A9%DB%8C%D9%84%D9%88%D9%85%D8%AA%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AD%D8%AF%D8%A7%D9%82%D9%84-%D8%B3%D8%B1%D8%B9%D8%AA-30-%DA%A9%DB%8C%D9%84%D9%88%D9%85%D8%AA%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%81%D9%82%D8%B7-%D8%B9%D8%A8%D9%88%D8%B1-%D8%A7%D8%B3%D8%A8-%D8%B3%D9%88%D8%A7%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%81%D9%82%D8%B7-%D8%B9%D8%A8%D9%88%D8%B1-%D8%AF%D9%88%DA%86%D8%B1%D8%AE%D9%87.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B1%D8%A7%D9%87-%DB%8C%DA%A9-%D8%B7%D8%B1%D9%81%D9%87.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AE%DB%8C%D8%A7%D8%A8%D8%A7%D9%86-%DB%8C%DA%A9-%D8%B7%D8%B1%D9%81%D9%87.gif",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AD%D8%AF%D8%A7%D9%82%D9%84-%D8%B3%D8%B1%D8%B9%D8%AA-%D8%AF%D8%B1-%D8%AE%D8%B7-%D9%87%D8%A7%DB%8C-%D8%B9%D8%A8%D9%88%D8%B1.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AD%D8%AF%D8%A7%DA%A9%D8%AB%D8%B1-%D8%B3%D8%B1%D8%B9%D8%AA-%D8%AF%D8%B1-%D8%AE%D8%B7-%D9%87%D8%A7%DB%8C-%D8%B9%D8%A8%D9%88%D8%B1.gif",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%85%D8%B3%DB%8C%D8%B1-%DA%A9%D8%A7%D9%85%DB%8C%D9%88%D9%86-%D8%AD%D8%A7%D9%85%D9%84-%DA%A9%D8%A7%D9%84%D8%A7%DB%8C-%D8%AE%D8%B7%D8%B1%D9%86%D8%A7%DA%A9.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%B9%D8%A8%D9%88%D8%B1-%D9%81%D9%82%D8%B7-%D8%A8%D8%A7-%D8%B2%D9%86%D8%AC%DB%8C%D8%B1-%DA%86%D8%B1%D8%AE.jpg",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D9%81%D9%82%D8%B7-%D8%B9%D8%A8%D9%88%D8%B1-%D8%AF%D9%88%DA%86%D8%B1%D8%AE%D9%87-%D9%88-%D9%BE%DB%8C%D8%A7%D8%AF%D9%87.png",
        "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AE%D8%B7-%D9%88%DB%8C%DA%98%D9%87-%D8%B9%D8%A8%D9%88%D8%B1.png",


        )


    inner class MyViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val textView = itemView.findViewById<TextView>(R.id.textView3)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder3 {
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycler, parent, false)
        return MyViewHolder3(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder3, position: Int) {

        holder.textView.text = nameList[position]

        // استفاده از کتابخانه Glide برای بارگذاری تصاویر از اینترنت به ImageView
        Glide.with(context)
            .load(imageUrls[position])
//            .placeholder(R.drawable.placeholder_image) // تصویر پیشفرض در صورت بارگذاری تا زمان دریافت تصویر از اینترنت
//            .error(R.drawable.error_image) // تصویر پیشفرض در صورت بروز خطا در بارگذاری تصویر
            .into(holder.imageView)


    }
}