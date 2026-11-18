package uk.ac.tees.mad.s3633826.pocketplant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The Room database for PocketPlant. Version 1 for Sprint 2 US-04.
 *
 * When Sprint 3 adds columns (scientific_name, image_url, watering_days), the version
 * bumps to 2 and a Migration from 1 to 2 is added here.
 */
@Database(entities = [PlantEntity::class], version = 1, exportSchema = true)
abstract class PocketPlantDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao
}
