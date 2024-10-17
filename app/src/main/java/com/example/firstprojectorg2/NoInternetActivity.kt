package com.example.firstprojectorg2


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.example.firstprojectorg2.databinding.ActivityNoInternetBinding

class NoInternetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoInternetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // تنظیم انیمیشن Lottie
        binding.lottieAnimationView.apply {
            setAnimation(R.raw.no_internet)
            playAnimation()
            repeatCount = LottieDrawable.INFINITE
        }

        binding.tvMessage.setOnClickListener {
            val intent = Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (NetworkUtils.isNetworkAvailable(this)) {
            finish()
        }
    }
}
