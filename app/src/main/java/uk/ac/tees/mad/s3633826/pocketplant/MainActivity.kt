package uk.ac.tees.mad.s3633826.pocketplant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uk.ac.tees.mad.s3633826.pocketplant.screens.SplashScreen
import uk.ac.tees.mad.s3633826.pocketplant.ui.theme.PocketPlantTheme

/**
 * The single Activity hosting the whole Compose UI.
 *
 * Sprint 1, commit 4: now shows the SplashScreen composable. After 1.5 seconds the splash
 * calls back, but there is nowhere to go yet; the next commit adds navigation and the
 * placeholder My Plants screen that the splash transitions to.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PocketPlantTheme {
                SplashScreen(onTimeout = { /* Navigation is added in the next commit. */ })
            }
        }
    }
}
