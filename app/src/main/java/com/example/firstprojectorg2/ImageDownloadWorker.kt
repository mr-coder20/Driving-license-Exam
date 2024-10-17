package com.example.firstprojectorg2

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImageDownloadWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val imageUrl = inputData.getString("IMAGE_URL") ?: return Result.failure()

        Glide.with(applicationContext)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .preload()

        return Result.success()
    }
}
