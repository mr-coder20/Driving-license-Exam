package com.example.firstprojectorg2

import MyAdapterAzmon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstprojectorg2.databinding.ActivityAzmon2Binding
import com.google.android.material.appbar.AppBarLayout

class AzmonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAzmon2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAzmon2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbarLayout=binding.toolbarLayout
toolbarLayout.expandedTitleTextSize= 80F


 binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{appBarLayout, verticalOffset ->
     val isExpanded = verticalOffset == 0
     val isCollapsed = Math.abs(verticalOffset) == appBarLayout.totalScrollRange
     if (isExpanded) {
         toolbarLayout.title=""
     } else if (isCollapsed) {
         toolbarLayout.title="آزمون ها"

     } else {
         toolbarLayout.title=""
     }
 })
        toolbarLayout.setCollapsedTitleTypeface(ResourcesCompat.getFont(this, R.font.iranyekan))
        toolbarLayout.setExpandedTitleTypeface(ResourcesCompat.getFont(this, R.font.iranyekan))
        toolbarLayout.expandedTitleGravity= Gravity.BOTTOM or Gravity.CENTER
        toolbarLayout.collapsedTitleGravity= Gravity.CENTER



        binding.recyclerViewAzmon.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewAzmon.adapter = MyAdapterAzmon(this)



    }
}