package uk.ac.tees.mad.s3633826.pocketplant.navigation

/**
 * Single source of truth for navigation route names. Using string constants keeps the
 * NavHost and the navigate() calls in sync without magic strings scattered around.
 *
 * Sprint 2: three routes (splash, my_plants, add_plant). Sprint 3 adds
 * plant_detail/{plantId}.
 */
object Destinations {
    const val SPLASH = "splash"
    const val MY_PLANTS = "my_plants"
    const val ADD_PLANT = "add_plant"
}
