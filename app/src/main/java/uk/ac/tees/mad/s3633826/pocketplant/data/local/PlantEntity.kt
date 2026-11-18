package uk.ac.tees.mad.s3633826.pocketplant.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database row for a plant.
 *
 * Deliberately kept to the fields Sprint 2 US-04 needs. Later sprints will add columns
 * like scientific_name, image_url and watering_days with Room migrations.
 */
@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "common_name") val commonName: String,
    @ColumnInfo(name = "nickname") val nickname: String,
    @ColumnInfo(name = "added_at") val addedAt: Long,
)
