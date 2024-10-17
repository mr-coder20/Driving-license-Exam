package TabloHa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstprojectorg2.MyAdapter.MyAdapter3
import com.example.firstprojectorg2.databinding.ActivityTabloEntezameBinding

class ActivityTabloEntezame : AppCompatActivity() {
    private lateinit var binding : ActivityTabloEntezameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTabloEntezameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView3.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView3.adapter = MyAdapter3(this)
    }
}