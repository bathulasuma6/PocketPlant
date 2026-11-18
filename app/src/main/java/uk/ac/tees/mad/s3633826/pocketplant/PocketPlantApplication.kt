package uk.ac.tees.mad.s3633826.pocketplant

import android.app.Application
import androidx.room.Room
import uk.ac.tees.mad.s3633826.pocketplant.data.PlantsRepository
import uk.ac.tees.mad.s3633826.pocketplant.data.local.PocketPlantDatabase

/**
 * Lightweight service locator for Sprint 2.
 *
 * Builds the Room database once per process and exposes a single [PlantsRepository]
 * that the UI reads through. A full DI framework (Hilt or Koin) is not needed at this
 * scale; a plain Application-held singleton keeps the setup explicit and easy to read.
 */
class PocketPlantApplication : Application() {

    lateinit var repository: PlantsRepository
        private set

    override fun onCreate() {
        super.onCreate()

        val database = Room.databaseBuilder(
            context = applicationContext,
            klass = PocketPlantDatabase::class.java,
            name = DATABASE_NAME,
        ).build()

        repository = PlantsRepository(dao = database.plantDao())
    }

    private companion object {
        const val DATABASE_NAME = "pocketplant.db"
    }
}
