package org.d3if4067.hitungbmr.network

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class UpdateWorker(
    context: Context, workerParams: WorkerParameters
) : Worker(context, workerParams) {

//    private val notificationId = 44

    override fun doWork(): Result {
        Log.d("Worker", "Menjalankan proses background..")

        return Result.success()
    }
}