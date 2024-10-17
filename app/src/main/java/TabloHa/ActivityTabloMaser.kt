package TabloHa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstprojectorg2.MyAdapter.MyAdapter4
import com.example.firstprojectorg2.databinding.ActivityTabloMaserBinding

class ActivityTabloMaser : AppCompatActivity() {
    private lateinit var binding: ActivityTabloMaserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTabloMaserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView4.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView4.adapter = MyAdapter4(this)



    }
}