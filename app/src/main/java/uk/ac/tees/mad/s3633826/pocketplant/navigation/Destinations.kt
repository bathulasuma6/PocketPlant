package uk.ac.tees.mad.s3633826.pocketplant.navigation

/**
 * Single source of truth for navigation route names. Using string constants keeps the
 * NavHost and the navigate() calls in sync without magic strings scattered around.
 *
 * Sprint 1: two routes (splash, my_plants). Sprint 2 and later add add_plant and
 * plant_detail/{plantId}.
 */
object Destinations {
    const val SPLASH = "splash"
    const val MY_PLANTS = "my_plants"
}
