package com.example.firstprojectorg2

import TabloHa.ActivityTabloEkhbare
import TabloHa.ActivityTabloEntezame
import TabloHa.ActivityTabloMaser
import TabloHa.ActivityTobloEkhtare
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.firstprojectorg2.databinding.ActivityTabloBinding
import com.google.android.material.appbar.AppBarLayout

class Tablo : AppCompatActivity() {
    private lateinit var binding: ActivityTabloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTabloBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val toolbarLayout=binding.toolbarLayout
        toolbarLayout.expandedTitleTextSize= 80F
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{ appBarLayout, verticalOffset ->
            val isExpanded = verticalOffset == 0
            val isCollapsed = Math.abs(verticalOffset) == appBarLayout.totalScrollRange
            if (isExpanded) {
                toolbarLayout.title=""
            } else if (isCollapsed) {
                toolbarLayout.title="تابلو ها"

            } else {
                toolbarLayout.title=""
            }
        })
        toolbarLayout.setCollapsedTitleTypeface(ResourcesCompat.getFont(this, R.font.iranyekan))
        toolbarLayout.setExpandedTitleTypeface(ResourcesCompat.getFont(this, R.font.iranyekan))
        toolbarLayout.expandedTitleGravity= Gravity.BOTTOM or Gravity.CENTER
        toolbarLayout.collapsedTitleGravity= Gravity.CENTER
        binding.constraintLayout11.setOnClickListener {
            val intent = Intent(this, ActivityTabloEkhbare::class.java)
            startActivity(intent)
        }

        binding.constraintLayout7.setOnClickListener {
            val intent = Intent(this, ActivityTobloEkhtare::class.java)
            startActivity(intent)
        }

        binding.constraintLayout13.setOnClickListener {
            val intent = Intent(this, ActivityTabloEntezame::class.java)
            startActivity(intent)
        }

        binding.constraintLayout12.setOnClickListener {
            val intent = Intent(this, ActivityTabloMaser::class.java)
            startActivity(intent)
        }


        // 2اخباری
        val ekhbare =
            "https://tse1.mm.bing.net/th/id/OIG1.qI5AzAo4JKOp.RvfOrYR?pid=ImgGn"

        Glide.with(this)
            .load(ekhbare)
            .into(binding.imageViewEkhbare)
//
//        //3
//        val entezame =
//            "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%A7%DB%8C%D8%B3%D8%AA.png"
//
//        Glide.with(this)
//            .load(entezame)
//            .into(binding.imageViewEntezame)
//
        //4
        val rahnama =
            "https://mahdeelm.com/wp-content/uploads/2021/07/%D8%B1%D8%A7%D9%87%D9%86%D9%85%D8%A7%DB%8C-%D9%85%D8%B3%DB%8C%D8%B1-4.png"

        Glide.with(this)
            .load(rahnama)
            .into(binding.rahnama)
//
////5
//        val sheklHa =
//            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Regular_octagon.svg/640px-Regular_octagon.svg.png"
//
//        Glide.with(this)
//            .load(sheklHa)
//            .into(binding.imageViewShekle)
//
//
//        binding.constraintLayout7.setOnClickListener {
//
//            val intent = Intent(this, TabloEkhtare::class.java)
//            startActivity(intent)
//        }
//
//
//val imageUrl2 = "https://mahdeelm.com/wp-content/uploads/2021/08/%D8%AD%D9%82-%D8%AA%D9%82%D8%AF%D9%85-%D8%B9%D8%A8%D9%88%D8%B1-%D8%A8%D8%A7-%D9%88%D8%B3%DB%8C%D9%84%D9%87-%D9%86%D9%82%D9%84%DB%8C%D9%87-%D9%85%D9%82%D8%A7%D8%A8%D9%84-%D8%A7%D8%B3%D8%AA.png"
//        Glide.with(this)
//            .load(imageUrl2)
//            .into(binding.imageView3)


    }
}