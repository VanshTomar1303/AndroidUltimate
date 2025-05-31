package com.vansh.resoursesandqualifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vansh.resoursesandqualifier.ui.theme.ResoursesAndQualifierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResoursesAndQualifierTheme {

            }
        }
    }
}
/*
    Resources -> refer to none code thing your app need
    ex :- svg,png,string,etc

    drawable -> pictures,svg,vector graphics,etc.
    v24 -> qualifier -> it only used for a specific api version

    Resources are external files or content used in an Android app, separate from the code. Common types include:

Resource    Type	                Folder Name	Example
Layout	    res/layout	            XML files that define UI (e.g. activity_main.xml)
Drawable	res/drawable	        Images, shapes, or XML-based graphics
Strings	    res/values/strings.xml	Text resources (e.g. app_name)
Colors	    res/values/colors.xml	Color definitions
Dimensions	res/values/dimens.xml	Sizes like margins and padding
Styles	    res/values/styles.xml	Themes and styles for UI

Qualifiers are suffixes added to resource directories to provide alternative resources for different configurations.
 Android selects the most appropriate resource based on the device's current configuration.

 | Qualifier Type     | Example           | Purpose                          |
| ------------------ | ----------------- | -------------------------------- |
| **Language**       | `values-fr/`      | French language strings          |
| **Screen size**    | `layout-large/`   | Layout for large screens         |
| **Orientation**    | `layout-land/`    | Layout for landscape orientation |
| **Density**        | `drawable-hdpi/`  | Images for high-density screens  |
| **Night mode**     | `values-night/`   | Dark mode colors or styles       |
| **API level**      | `values-v21/`     | Values for API 21+ (Lollipop)    |
| **Smallest width** | `layout-sw600dp/` | Tablets with min 600dp width     |

 */
