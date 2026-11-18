package uk.ac.tees.mad.s3633826.pocketplant.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Database access for the plants table.
 *
 * Sprint 2: observeAll (US-04), insert (US-05), delete and findById (US-06).
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
     * REPLACE lets Undo re-insert a deleted row at its original id.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: PlantEntity): Long

    /**
     * Returns the plant row with the given id, or null if it no longer exists.
     * Used by the repository to grab the full row before deletion so Undo can
     * restore it with every field intact.
     */
    @Query("SELECT * FROM plants WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): PlantEntity?

    /** Deletes the given plant row from the table. */
    @Delete
    suspend fun delete(plant: PlantEntity)
}
