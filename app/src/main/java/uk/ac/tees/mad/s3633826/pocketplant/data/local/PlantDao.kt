package uk.ac.tees.mad.s3633826.pocketplant.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Database access for the plants table.
 *
 * Sprint 2 US-04 added read-all. US-05 adds insert so the user can save a new plant.
 * Delete is added in US-06 on its own feature branch.
 */
@Dao
interface PlantDao {

    /**
     * Observes the plants table, newest first. Room emits a new list every time the
     * underlying rows change, so the UI can collect this Flow and stay up to date.
     */
    @Query("SELECT * FROM plants ORDER BY added_at DESC")
    fun observeAll(): Flow<List<PlantEntity>>

    /**
     * Inserts a new plant row and returns the auto-generated primary key.
     * REPLACE lets Undo (Sprint 2 US-06) re-insert a deleted row at its original id.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: PlantEntity): Long
}
