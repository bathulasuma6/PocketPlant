package uk.ac.tees.mad.s3633826.pocketplant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Plant-green Material 3 colour schemes. Kept intentionally small in Sprint 1; later sprints
// can extend with container, surface variant, tertiary and error tones.
private val LightPlantScheme = lightColorScheme(
    primary = PlantGreen,
    onPrimary = LeafCream,
    secondary = PlantGreenLight,
    onSecondary = NightSoil,
    background = LeafCream,
    onBackground = NightSoil,
    surface = LeafCream,
    onSurface = BarkBrown,
)

private val DarkPlantScheme = darkColorScheme(
    primary = NightLeaf,
    onPrimary = NightSoil,
    secondary = PlantGreenLight,
    onSecondary = NightSoil,
    background = NightSoil,
    onBackground = LeafCream,
    surface = NightSoil,
    onSurface = LeafCream,
)

/**
 * Wraps the app content in the Material 3 theme. Reads the system light/dark setting and
 * applies the matching scheme. All Composables under this call read colours from
 * MaterialTheme.colorScheme and typography from MaterialTheme.typography.
 */
@Composable
fun PocketPlantTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkPlantScheme else LightPlantScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = PocketPlantTypography,
        content = content,
    )
}
