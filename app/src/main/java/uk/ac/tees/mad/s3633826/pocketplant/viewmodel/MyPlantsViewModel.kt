package uk.ac.tees.mad.s3633826.pocketplant.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uk.ac.tees.mad.s3633826.pocketplant.data.Plant
import uk.ac.tees.mad.s3633826.pocketplant.data.PlantsRepository

/**
 * Holds the list of plants for MyPlantsScreen (US-04) and the delete + Undo flow (US-06).
 *
 * - [plants] is the live list from Room.
 * - [deleteEvent] holds the most recently deleted plant plus an [DeleteEvent.ackId] that
 *   the Snackbar uses to tell the ViewModel when it is finished showing. Keeping a
 *   per-event id prevents a new delete's Snackbar from being acknowledged by an older
 *   one.
 */
class MyPlantsViewModel(private val repository: PlantsRepository) : ViewModel() {

    val plants: StateFlow<List<Plant>> =
        repository.observeAll().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList(),
        )

    private val _deleteEvent = MutableStateFlow<DeleteEvent?>(null)
    val deleteEvent: StateFlow<DeleteEvent?> = _deleteEvent.asStateFlow()

    private var nextAckId: Long = 0L

    fun onDelete(id: Long) {
        viewModelScope.launch {
            val removed = repository.deleteAndReturn(id) ?: return@launch
            _deleteEvent.value = DeleteEvent(plant = removed, ackId = ++nextAckId)
        }
    }

    fun onUndoDelete() {
        val event = _deleteEvent.value ?: return
        viewModelScope.launch {
            repository.restore(event.plant)
            _deleteEvent.value = null
        }
    }

    fun acknowledgeDelete(ackId: Long) {
        val current = _deleteEvent.value ?: return
        if (current.ackId == ackId) {
            _deleteEvent.value = null
        }
    }

    data class DeleteEvent(val plant: Plant, val ackId: Long)

    class Factory(private val repository: PlantsRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MyPlantsViewModel(repository) as T
    }
}
