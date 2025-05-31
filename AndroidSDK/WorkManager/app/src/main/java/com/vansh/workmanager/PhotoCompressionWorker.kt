package com.vansh.workmanager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

class PhotoCompressionWorker(
    private val appContext: Context,
    private val params: WorkerParameters
): CoroutineWorker(appContext,params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val stringUri = params.inputData.getString(KEY_URI)
            val compression = params.inputData.getLong(
                KEY_COMPRESSION,
                0
            )
            val uri = Uri.parse(stringUri)
            val bytes = appContext.contentResolver.openInputStream(uri)?.use {
                it.readBytes()
            } ?: return@withContext Result.failure()

            val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
            var outputBytes: ByteArray
            var quality = 100

            do {
                val outputStream = ByteArrayOutputStream()
                outputStream.use {
                    bitmap.compress(Bitmap.CompressFormat.JPEG,quality,outputStream)
                    outputBytes = outputStream.toByteArray()
                    quality -= (quality * 0.1).roundToInt()
                }
            }while (outputBytes.size > compression && quality>5)

            val file = File(appContext.cacheDir,"${params.id}.jpg")
            file.writeBytes(outputBytes)

            Result.success(
                workDataOf(
                    KEY_RESULT to file.absolutePath
                )
            )

        }
    }

    companion object{
        const val KEY_URI = "KEY_URI"
        const val KEY_COMPRESSION = "KEY_COMPRESSION"
        const val KEY_RESULT = "KEY_RESULT"
    }

}