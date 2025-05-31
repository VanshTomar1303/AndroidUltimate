package com.vansh.intentandintentfilter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
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
import com.vansh.intentandintentfilter.ui.theme.IntentAndIntentFilterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentAndIntentFilterTheme {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
//                        onClick ={
//                        // Explicit intent
//                            // To start second activity
////                            Intent(applicationContext, SecondActivity::class.java).also {
////                                startActivity(it)
////                            }
//
//                            // to see package name of emulator/phone
//                            // adb shell  -> pm list packages
//                            // To open another app using our own
//                            Intent(Intent.ACTION_MAIN).also {
//                                it.`package` = "com.android.chrome"
//                                try{
//                                    startActivity(it)
//                                }catch (e: ActivityNotFoundException){
//                                    Toast.makeText(
//                                        applicationContext,
//                                        "Activity not found",
//                                        Toast.LENGTH_LONG
//                                    ).show()
//                                }
//                            }
//                         }
                        onClick = {
                            // Implicit intent
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_EMAIL,arrayOf("vansh.tomar.1303@gmail.com"))
                                putExtra(Intent.EXTRA_SUBJECT,"This is implicit intent")
                                putExtra(Intent.EXTRA_TEXT,"hsdbfksjndfsfdnkdshfcsikjhbfjsa")
                            }
                            if(intent.resolveActivity(packageManager) != null){
                                startActivity(intent)
                            }
                        }

                    ) {
                        Text(
                            "Go to second activity"
                        )
                    }
                }
            }
        }
    }
}
/*
    An Intent is a messaging object you can use to request an action from another app component (like activities, services, or broadcast receivers).

    Types of Intents
Explicit Intent:
You specify the exact component (activity/service) to start.
Used within your own app.

val intent = Intent(this, SecondActivity::class.java)
startActivity(intent)


Implicit Intent:
You do not specify the exact component.
You just declare the action to be performed, and the Android system finds the appropriate component.

val intent = Intent(Intent.ACTION_VIEW)
intent.data = Uri.parse("https://www.google.com")
startActivity(intent)


🔹 IntentFilter
An IntentFilter is a declaration in the manifest file or code that specifies which intents a component can respond to.
It contains:
One or more actions (android.intent.action.VIEW, etc.)
Optionally categories and data types (like http, image/*, etc.)


 <activity android:name=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>

🔄 How They Work Together
When an Intent is sent:
Android checks all components with matching IntentFilters.
If a match is found (for implicit intents), it either:
Starts that component, or
Shows a chooser if multiple apps match.
 */


 */