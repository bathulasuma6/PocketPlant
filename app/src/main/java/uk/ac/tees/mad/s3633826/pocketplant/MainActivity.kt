package uk.ac.tees.mad.s3633826.pocketplant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uk.ac.tees.mad.s3633826.pocketplant.ui.theme.PocketPlantTheme

/**
 * The single Activity hosting the whole Compose UI.
 *
 * Sprint 1, commit 5: MainActivity simply wraps PocketPlantApp in the plant-green theme.
 * PocketPlantApp owns the navigation graph (splash -> my_plants) and every route.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PocketPlantTheme {
                PocketPlantApp()
            }
        }
    }
}
