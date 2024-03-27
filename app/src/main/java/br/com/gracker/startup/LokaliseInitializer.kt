package br.com.gracker.startup

import android.content.Context
import androidx.startup.Initializer
import br.com.bmattoso.gracker.BuildConfig
import com.lokalise.sdk.Lokalise

/**
 * Referenced by app/main/AndroidManifest.xml
 */
class LokaliseInitializer : Initializer<Lokalise> {
    override fun create(context: Context): Lokalise {
        return Lokalise.apply {
            init(
                appContext = context,
                sdkToken = BuildConfig.LOKALISE_SDK_TOKEN,
                projectId = BuildConfig.LOKALISE_PROJECT_ID,
            )
            isPreRelease = true
            updateTranslations()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
