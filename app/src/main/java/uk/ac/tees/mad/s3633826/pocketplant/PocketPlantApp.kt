package uk.ac.tees.mad.s3633826.pocketplant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.ac.tees.mad.s3633826.pocketplant.navigation.Destinations
import uk.ac.tees.mad.s3633826.pocketplant.screens.MyPlantsScreen
import uk.ac.tees.mad.s3633826.pocketplant.screens.SplashScreen
import uk.ac.tees.mad.s3633826.pocketplant.viewmodel.MyPlantsViewModel

/**
 * The top-level Compose entry point. Sets up the NavHost and wires up the ViewModel.
 *
 * Sprint 2 commit 6 (final): the My Plants route now pulls its list from
 * [MyPlantsViewModel], which in turn loads from Room via [PlantsRepository]. The
 * hardcoded sample list is gone. If the database is empty, the screen shows the
 * Sprint 1 empty state; as soon as rows exist, the LazyColumn renders them.
 */
@Composable
fun PocketPlantApp() {
    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as PocketPlantApplication

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
            val viewModel: MyPlantsViewModel = viewModel(
                factory = MyPlantsViewModel.Factory(application.repository),
            )
            val plants by viewModel.plants.collectAsStateWithLifecycle()
            MyPlantsScreen(plants = plants)
        }
    }
}
