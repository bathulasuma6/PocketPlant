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

/**
 * The single Activity hosting the whole Compose UI.
 *
 * Sprint 1, commit 2: an empty Android skeleton. The app launches, uses the default
 * Material 3 theme, and shows the app name centred on the screen. The plant-green theme,
 * splash screen and navigation are added in later commits of Sprint 1.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNamePlaceholder()
                }
            }
        }
    }
}

/**
 * Temporary placeholder: shows just the app name while the real screens are built up
 * across the remaining Sprint 1 commits.
 */
@Composable
private fun AppNamePlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}
