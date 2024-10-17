package TabloHa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstprojectorg2.MyAdapter.MyAdapter
import com.example.firstprojectorg2.R
import com.example.firstprojectorg2.databinding.ActivityTabloEkhbareBinding
import com.google.android.material.appbar.AppBarLayout

class ActivityTabloEkhbare : AppCompatActivity() {
    private lateinit var binding: ActivityTabloEkhbareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTabloEkhbareBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = MyAdapter(this)

        val toolbarLayout=binding.toolbarLayout
        toolbarLayout.expandedTitleTextSize= 80F
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{ appBarLayout, verticalOffset ->
            val isExpanded = verticalOffset == 0
            val isCollapsed = Math.abs(verticalOffset) == appBarLayout.totalScrollRange
            if (isExpanded) {
                toolbarLayout.title=""
            } else if (isCollapsed) {
                toolbarLayout.title="تابلوهای آگاهی دهنده یا اخباری"

            } else {
                toolbarLayout.title=""
            }
        })
        toolbarLayout.setCollapsedTitleTypeface(ResourcesCompat.getFont(this, R.font.iranyekan))
        toolbarLayout.setExpandedTitleTypeface(ResourcesCompat.getFont(this, R.font.iranyekan))
        toolbarLayout.expandedTitleGravity= Gravity.BOTTOM or Gravity.CENTER
        toolbarLayout.collapsedTitleGravity= Gravity.CENTER
    }
}