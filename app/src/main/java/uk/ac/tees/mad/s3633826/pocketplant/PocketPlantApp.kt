package uk.ac.tees.mad.s3633826.pocketplant

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.s3633826.pocketplant.navigation.Destinations
import uk.ac.tees.mad.s3633826.pocketplant.screens.MyPlantsScreen
import uk.ac.tees.mad.s3633826.pocketplant.screens.SplashScreen

/**
 * The top-level Compose entry point. Sets up a NavHost with the Sprint 1 routes.
 *
 * Flow:
 *  - Start on [Destinations.SPLASH]. After 1.5 seconds the splash calls back and we
 *    navigate to [Destinations.MY_PLANTS], popping the splash so Back from the home
 *    screen exits the app instead of going back to the splash.
 *  - Sprint 2 and later register more routes here (add_plant, plant_detail/{plantId}).
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
            MyPlantsScreen()
        }
    }
}
