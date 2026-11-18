package uk.ac.tees.mad.s3633826.pocketplant.data

/**
 * The plant the user has saved.
 *
 * Sprint 2 only needs three things to show a plant on the list:
 *  - [id] so each row can be identified when we later swipe-to-delete (Sprint 3+).
 *  - [commonName] the plant's everyday name, e.g. "Monstera".
 *  - [nickname] the user's personal name for it, e.g. "Monstie".
 *  - [addedAt] when the user saved it, so the list can be ordered newest-first.
 *
 * This is a plain Kotlin data class (no Room annotations), separate from the Room
 * entity defined later. Keeping the domain model free of database-specific code means
 * the UI never depends on Room directly.
 */
data class Plant(
    val id: Long,
    val commonName: String,
    val nickname: String,
    val addedAt: Long,
)
