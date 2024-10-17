package com.example.firstprojectorg2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstprojectorg2.MyAdapter.MyAdapterAzmonTabloHa
import com.example.firstprojectorg2.R

class ColerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coler)

        val imageUrls = listOf(
            "https://fs.noorgram.ir/pic/2020/18/1.jpg",
            "https://fs.noorgram.ir/pic/2020/18/2.jpg",
            "https://fs.noorgram.ir/pic/2020/18/3.jpg",
            "https://fs.noorgram.ir/pic/2020/18/4.jpg",
            "https://fs.noorgram.ir/pic/2020/18/5.jpg",
            "https://fs.noorgram.ir/pic/2020/18/6.jpg",
            "https://fs.noorgram.ir/pic/2020/18/7.jpg",
            "https://fs.noorgram.ir/pic/2020/18/8.jpg"
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewAzmonTabloHa)
        val layoutManager = GridLayoutManager(this, 2)

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if ((position + 1) % 3 == 0) 2 else 1
            }
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MyAdapterAzmonTabloHa(this, imageUrls)


    }

}
