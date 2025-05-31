package com.vansh.workmanager

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import coil.compose.AsyncImage
import com.vansh.workmanager.ui.theme.WorkManagerTheme

/*
    WorkManager :
        * Sync data
        * long time task
        * guarantee your task is executed
 */
class MainActivity : ComponentActivity() {
    private lateinit var workManger: WorkManager
    private val viewModel by viewModels<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        workManger = WorkManager.getInstance(applicationContext)
        enableEdgeToEdge()
        setContent {
            WorkManagerTheme {
                val workerResult = viewModel.workId?.let { id->
                    workManger.getWorkInfoByIdLiveData(id).observeAsState().value
                }
                LaunchedEffect(key1 = workerResult?.outputData) {
                    if (workerResult != null) {
                        if(workerResult.outputData!=null){
                            val filePath = workerResult?.outputData?.getString(
                                PhotoCompressionWorker.KEY_RESULT
                            )
                            filePath?.let {
                                val bitmap = BitmapFactory.decodeFile(it)
                                viewModel.updateCompressedBitmap(bitmap)
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    viewModel.uri?.let {
                        Text(text = "UnCompressed Uri")
                        AsyncImage(model = it, contentDescription = null , modifier = Modifier.size(500.dp,200.dp))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    viewModel.compressedBitMap?.let {
                        Text(text = "Compressed Uri")
                        Image(bitmap = it.asImageBitmap(), contentDescription = null, modifier = Modifier.size(500.dp,200.dp) )
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
           intent?.getParcelableExtra(Intent.EXTRA_STREAM,Uri::class.java)
        }else{
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }?: return

        viewModel.updateUri(uri)

        val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
            .setInputData(
                workDataOf(
                    PhotoCompressionWorker.KEY_URI to uri.toString(),
                    PhotoCompressionWorker.KEY_COMPRESSION to 1024 * 20L
                )
            )
            .setConstraints(Constraints(
                requiresStorageNotLow = true
            ))
            .build()
        viewModel.updateWorkId(request.id)
        workManger.enqueue(request)
    }
}

