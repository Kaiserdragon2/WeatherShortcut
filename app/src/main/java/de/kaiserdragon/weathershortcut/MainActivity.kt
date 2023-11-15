package de.kaiserdragon.weathershortcut;

import android.app.AlertDialog
import android.content.ComponentName
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.MATCH_DEFAULT_ONLY
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the target app is installed
        if (isWeatherAppInstalled()) {
            val shortcutIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://...") // Replace with the correct URL
                component = ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/com.google.android.apps.search.weather.WeatherExportedActivity")
            }
            startActivity(shortcutIntent)
            finish()
        } else {
            // Display a pop-up informing the user that the app is not installed
            showAppNotInstalledDialog()
        }
    }

    private fun isWeatherAppInstalled(): Boolean {
        val packageName = "com.google.apple.googlequicksearchbox"
        val activityName = "com.google.android.apps.search.weather.WeatherExportedActivity"
        val pm: PackageManager = packageManager
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            component = ComponentName(packageName, activityName)
        }

        val resolveInfo: ResolveInfo?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            resolveInfo = pm.resolveActivity(intent, PackageManager.ResolveInfoFlags.of(MATCH_DEFAULT_ONLY.toLong()))
        } else {
            @Suppress("DEPRECATION")
            resolveInfo = pm.resolveActivity(intent, MATCH_DEFAULT_ONLY)
        }

        return resolveInfo != null
    }


    private fun showAppNotInstalledDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Google App Not Installed")
            .setMessage("The required app is not installed. Please install it to use this feature.")
            .setPositiveButton("OK") { dialogInterface: DialogInterface, _: Int ->
                // You can handle the OK button click if needed
                dialogInterface.dismiss()
                finish()
            }
            .show()
    }
}
