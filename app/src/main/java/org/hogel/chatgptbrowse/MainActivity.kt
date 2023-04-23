package org.hogel.chatgptbrowse

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent

class MainActivity : AppCompatActivity() {
    private val CHATGPT_URL = Uri.parse("https://chat.openai.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val colorSchemeParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(getColor(R.color.toolbar))
            .build()

        val customTabsIntent = CustomTabsIntent.Builder()
            .setUrlBarHidingEnabled(true)
            .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
            .setShowTitle(true)
            .setDefaultColorSchemeParams(colorSchemeParams)
            .build()
        customTabsIntent.intent.data = CHATGPT_URL

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                finish()
            }
        }.launch(customTabsIntent.intent)
    }
}
