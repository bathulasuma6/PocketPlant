package uk.ac.tees.mad.s3633826.pocketplant

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.s3633826.pocketplant.data.Plant
import uk.ac.tees.mad.s3633826.pocketplant.navigation.Destinations
import uk.ac.tees.mad.s3633826.pocketplant.screens.MyPlantsScreen
import uk.ac.tees.mad.s3633826.pocketplant.screens.SplashScreen

/**
 * The top-level Compose entry point. Sets up a NavHost with the Sprint 1/2 routes.
 *
 * Sprint 2 commit 2: MyPlantsScreen now receives a plants list. For now the list comes
 * from a hardcoded sample so we can see the UI render real rows. The next few commits
 * swap this hardcoded list for Room-backed data loaded through a ViewModel.
 */
@Composable
fun PocketPlantApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.SPLASH,
    ) {
        composable(Destinations.SPLASH) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Destinations.MY_PLANTS) {
                        popUpTo(Destinations.SPLASH) { inclusive = true }
                    }
                },
            )
        }
        composable(Destinations.MY_PLANTS) {
            // Temporary hardcoded sample list; replaced by the ViewModel in commit 6.
            MyPlantsScreen(plants = SAMPLE_PLANTS)
        }
    }
}

private val SAMPLE_PLANTS = listOf(
    Plant(id = 1, commonName = "Monstera", nickname = "Monstie", addedAt = 0L),
    Plant(id = 2, commonName = "Snake plant", nickname = "Sneaky", addedAt = 0L),
    Plant(id = 3, commonName = "Pothos", nickname = "Pothy", addedAt = 0L),
)
