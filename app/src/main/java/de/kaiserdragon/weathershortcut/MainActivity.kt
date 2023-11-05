package de.kaiserdragon.weathershortcut;
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val shortcutIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://...") // Replace with the correct URL
                component = ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/com.google.android.apps.search.weather.WeatherExportedActivity")
            }
            startActivity(shortcutIntent)
       finish()
    }
}



