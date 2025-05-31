package com.vansh.broadcastandbroadcastreceiver

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vansh.broadcastandbroadcastreceiver.ui.theme.BroadCastAndBroadCastReceiverTheme

class MainActivity : ComponentActivity() {
// To send a broadcast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BroadCastAndBroadCastReceiverTheme {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            sendBroadcast(
                                Intent("TEST_ACTION")
                            )
                        }) {
                            Text(text = "SEND")
                        }
                    }
            }
        }
    }


}

/*
    To Receive a broadcast --

    class MainActivity : ComponentActivity() {
    private val airPlaneModeReceiver = AeroPlaneModeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(
            airPlaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            )
        enableEdgeToEdge()
        setContent {
            BroadCastAndBroadCastReceiverTheme {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
    }
}
 */

