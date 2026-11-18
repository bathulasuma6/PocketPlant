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
import uk.ac.tees.mad.s3633826.pocketplant.screens.AddPlantScreen
import uk.ac.tees.mad.s3633826.pocketplant.screens.MyPlantsScreen
import uk.ac.tees.mad.s3633826.pocketplant.screens.SplashScreen
import uk.ac.tees.mad.s3633826.pocketplant.viewmodel.AddPlantViewModel
import uk.ac.tees.mad.s3633826.pocketplant.viewmodel.MyPlantsViewModel

/**
 * The top-level Compose entry point. Sets up the NavHost and wires up the ViewModels.
 *
 * Sprint 2 routes:
 *  - splash (US-01): 1.5-second welcome, then navigates to my_plants and pops itself.
 *  - my_plants (US-04): shows the list, FAB opens add_plant.
 *  - add_plant (US-05): form; Back arrow or successful Save returns to my_plants.
 *
 * Sprint 2 US-06 adds swipe-to-delete on my_plants. Sprint 3 adds plant_detail.
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
            MyPlantsScreen(
                plants = plants,
                onAddClick = { navController.navigate(Destinations.ADD_PLANT) },
            )
        }
        composable(Destinations.ADD_PLANT) {
            val viewModel: AddPlantViewModel = viewModel(
                factory = AddPlantViewModel.Factory(application.repository),
            )
            AddPlantScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() },
            )
        }
    }
}
