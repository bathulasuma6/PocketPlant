package uk.ac.tees.mad.s3633826.pocketplant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.ac.tees.mad.s3633826.pocketplant.ui.theme.PocketPlantTheme

/**
 * The single Activity hosting the whole Compose UI.
 *
 * Sprint 1, commit 3: content is now wrapped in PocketPlantTheme, so colours come from
 * the plant-green Material 3 scheme (light and dark). The splash screen and navigation
 * are added in later commits of this feature branch.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PocketPlantTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    AppNamePlaceholder()
                }
            }
        }
    }
}

@Composable
private fun AppNamePlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
