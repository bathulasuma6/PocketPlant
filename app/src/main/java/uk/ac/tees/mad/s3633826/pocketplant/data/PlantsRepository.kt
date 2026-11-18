package uk.ac.tees.mad.s3633826.pocketplant.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uk.ac.tees.mad.s3633826.pocketplant.data.local.PlantDao
import uk.ac.tees.mad.s3633826.pocketplant.data.local.PlantEntity

/**
 * The only thing that touches the Room database directly.
 *
 * The UI never imports Room types; it asks the repository for domain [Plant]s. This keeps
 * the UI testable without an Android instrumented runtime and makes it easy to swap the
 * data source later (for example to a remote API for the Perenual plant catalogue).
 */
class PlantsRepository(private val dao: PlantDao) {

    /**
     * Streams every saved plant to the caller as domain models, newest first.
     * Backed by the Room Flow in [PlantDao.observeAll], so updates are automatic.
     */
    fun observeAll(): Flow<List<Plant>> =
        dao.observeAll().map { rows -> rows.map(PlantEntity::toDomain) }

    /**
     * Saves a new plant typed in by the user. Trims whitespace and rejects blanks.
     * Returns the auto-generated row id so the caller (Sprint 2 US-06 Undo) can restore
     * the row at the same id later.
     */
    suspend fun addByHand(commonName: String, nickname: String, now: Long): Long {
        val cleanCommon = commonName.trim()
        val cleanNickname = nickname.trim()
        require(cleanCommon.isNotEmpty()) { "commonName must not be blank" }
        require(cleanNickname.isNotEmpty()) { "nickname must not be blank" }
        return dao.insert(
            PlantEntity(commonName = cleanCommon, nickname = cleanNickname, addedAt = now),
        )
    }
}

private fun PlantEntity.toDomain(): Plant =
    Plant(
        id = id,
        commonName = commonName,
        nickname = nickname,
        addedAt = addedAt,
    )
