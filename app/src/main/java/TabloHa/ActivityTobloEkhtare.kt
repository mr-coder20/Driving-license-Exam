package TabloHa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstprojectorg2.MyAdapter.MyAdapter2
import com.example.firstprojectorg2.databinding.ActivityTobloEkhtareBinding

class ActivityTobloEkhtare : AppCompatActivity() {
    private lateinit var binding: ActivityTobloEkhtareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTobloEkhtareBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//

        binding.recyclerView2.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView2.adapter = MyAdapter2(this)

    }
}