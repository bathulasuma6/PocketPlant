package uk.ac.tees.mad.s3633826.pocketplant.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import uk.ac.tees.mad.s3633826.pocketplant.data.Plant
import uk.ac.tees.mad.s3633826.pocketplant.data.PlantsRepository

/**
 * Holds the list of plants for MyPlantsScreen.
 *
 * Kept deliberately simple for Sprint 2 US-04:
 *  - [plants] is a StateFlow<List<Plant>>; an empty list means the screen shows the
 *    Sprint 1 empty state, a non-empty list shows the LazyColumn.
 *  - No separate "loading" state, no sealed class. The screen renders the current list
 *    the moment it has one.
 *
 * Sprint 2 US-05 (add) and US-06 (swipe-delete) add more state and methods here on
 * their own feature branches.
 */
class MyPlantsViewModel(repository: PlantsRepository) : ViewModel() {

    val plants: StateFlow<List<Plant>> =
        repository.observeAll().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList(),
        )

    /**
     * Factory used by the UI to construct the ViewModel with its repository dependency.
     * Kept inline so Compose can create it with viewModel(factory = Factory(repo)).
     */
    class Factory(private val repository: PlantsRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MyPlantsViewModel(repository) as T
    }
}
