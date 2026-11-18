package uk.ac.tees.mad.s3633826.pocketplant.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Database access for the plants table.
 *
 * Sprint 2 US-04 only needs read-all. Insert and delete are added in US-05 and US-06
 * on their own feature branches.
 */
@Dao
interface PlantDao {

    /**
     * Observes the plants table, newest first. Room emits a new list every time the
     * underlying rows change, so the UI can collect this Flow and stay up to date.
     */
    @Query("SELECT * FROM plants ORDER BY added_at DESC")
    fun observeAll(): Flow<List<PlantEntity>>
}
