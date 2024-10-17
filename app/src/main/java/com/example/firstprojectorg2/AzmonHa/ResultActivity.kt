package com.example.firstprojectorg2.AzmonHa

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstprojectorg2.Azmon_tabloHa.AzmonT1
import com.example.firstprojectorg2.Azmon_tabloHa.AzmonT2
import com.example.firstprojectorg2.Azmon_tabloHa.AzmonT3
import com.example.firstprojectorg2.Azmon_tabloHa.AzmonT4
import com.example.firstprojectorg2.Azmon_tabloHa.AzmonT5
import com.example.firstprojectorg2.Azmon_tabloHa.AzmonT6
import com.example.firstprojectorg2.Azmon_tabloHa.AzmonT7
import com.example.firstprojectorg2.Azmon_tabloHa.AzmonT8
import com.example.firstprojectorg2.R
import com.example.firstprojectorg2.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val correctCount = intent.getIntExtra("correctCount", 0)
        val incorrectCount = intent.getIntExtra("incorrectCount", 0)
        val examId = intent.getStringExtra("examId")

binding.badResultTextView.text="غلط\n\n${incorrectCount}"
        binding.badResultTextView.setTextColor(Color.RED)
        binding.goodResultTextView.text="درست\n\n${correctCount}"
        binding.goodResultTextView.setTextColor(Color.GREEN)


        val emojiRes = if (incorrectCount > 4) {
            R.raw.fail
        } else {
            R.raw.pass

        }
        binding.resultEmoji.setAnimation(emojiRes)

        if (incorrectCount > 4) {
            binding.statusTextView.text = "نتیجه\nمردود شدید"
            binding.statusTextView.setTextColor(Color.RED)
        } else {
            binding.statusTextView.text = "نتیجه\nقبول شدید"
            binding.statusTextView.setTextColor(Color.GREEN)
        }

        binding.showAnswersButton.setOnClickListener {
            val intent = when (examId) {
                "ActivityAzmon" -> Intent(this, ActivityAzmon::class.java)
                "Azmon2" -> Intent(this, Azmon2::class.java)
                "Azmon3" -> Intent(this, Azmon3::class.java) // کلاس آزمون دوم
                "Azmon4" -> Intent(this, Azmon4::class.java)
                "Azmon5" -> Intent(this, Azmon5::class.java)
                "Azmon6" -> Intent(this, Azmon6::class.java)
                "Azmon7" -> Intent(this, Azmon7::class.java)
                "Azmon8" -> Intent(this, Azmon8::class.java)
                "Azmon9" -> Intent(this, Azmon9::class.java)
                "Azmon10" -> Intent(this, Azmon10::class.java)
                "Azmon11" -> Intent(this, Azmon11::class.java)
                "Azmon12" -> Intent(this, Azmon12::class.java)
                "Azmon13" -> Intent(this, Azmon13::class.java)
                "Azmon14" -> Intent(this, Azmon14::class.java)
                "Azmon15" -> Intent(this, Azmon15::class.java)
                //برای ازمون تابلوها اضافه کردم
                "AzmonT1" -> Intent(this, AzmonT1::class.java)
                "AzmonT2" -> Intent(this, AzmonT2::class.java)
                "AzmonT3" -> Intent(this, AzmonT3::class.java)
                "AzmonT4" -> Intent(this, AzmonT4::class.java)
                "AzmonT5" -> Intent(this, AzmonT5::class.java)
                "AzmonT6" -> Intent(this, AzmonT6::class.java)
                "AzmonT7" -> Intent(this, AzmonT7::class.java)
                "AzmonT8" -> Intent(this, AzmonT8::class.java)
                else -> null
            }
            intent?.let {
                it.putExtra("showCorrectAnswers", true)
                startActivity(it)
                finish()
            }
        }
    }
}
