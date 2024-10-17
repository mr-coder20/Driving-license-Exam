package com.example.firstprojectorg2


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.work.*
import com.example.firstprojectorg2.databinding.ActivityMainBinding
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val batchSize = 50
    private var currentBatch = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        if (!NetworkUtils.isNetworkAvailable(this)) {
            val intent = Intent(this, NoInternetActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.constraintLayoutTablo.setOnClickListener {
            val intent = Intent(this, Tablo::class.java)
            startActivity(intent)
        }

        binding.idConstraintRead.setOnClickListener {
            val intent = Intent(this, ColerActivity::class.java)
            startActivity(intent)
        }

        binding.constraintLayoutTalae.setOnClickListener {
            val intent = Intent(this, GoldActivity::class.java)
            startActivity(intent)
        }

        binding.constraintLayoutAzmon.setOnClickListener {
            val intent = Intent(this, AzmonActivity::class.java)
            startActivity(intent)
        }

        // URLهای تصاویر را در اینجا تعریف کنید
        val imageUrls = arrayOf(
            "https://th.bing.com/th/id/OIG2.cfKn.gLhLxsPmgrBLAP_?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
            "https://th.bing.com/th/id/OIG4.lH0lqN_VPVqxAaAbjewb?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn",
            "https://th.bing.com/th/id/OIG1.lqf8aSGlwtu5JPv6flZK?w=270&h=270&c=6&r=0&o=5&dpr=1.3&pid=ImgGn"
            // ادامه URLها...
        )

        // تنظیم محدودیت های شبکه
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // ساخت و ارسال درخواست‌های دانلود به WorkManager به صورت دسته‌ای
        loadBatchImages(imageUrls, constraints)
    }

    private fun loadBatchImages(imageUrls: Array<String>, constraints: Constraints) {
        val startIndex = currentBatch * batchSize
        val endIndex = minOf((currentBatch + 1) * batchSize, imageUrls.size)
        if (startIndex >= imageUrls.size) return

        val workRequests = mutableListOf<WorkRequest>()

        for (i in startIndex until endIndex) {
            val inputData = Data.Builder()
                .putString("IMAGE_URL", imageUrls[i])
                .build()

            val workRequest = OneTimeWorkRequest.Builder(ImageDownloadWorker::class.java)
                .setInputData(inputData)
                .setConstraints(constraints)
                .build()

            workRequests.add(workRequest)
        }

        WorkManager.getInstance(this).enqueue(workRequests)
        currentBatch++

    }

    // پاک کردن کش به صورت دوره‌ای
    private fun clearCache() {
        Glide.get(applicationContext).clearDiskCache()
        Glide.get(applicationContext).clearMemory()
    }

    override fun onResume() {
        super.onResume()
        if (!NetworkUtils.isNetworkAvailable(this)) {
            val intent = Intent(this, NoInternetActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // تنظیم اندازه کش (مثلاً 100 مگابایت)
        val diskCacheSizeBytes = 1024 * 1024 * 100 // 100 MB
        builder.setDiskCache(ExternalCacheDiskCacheFactory(context, diskCacheSizeBytes))
    }

}
