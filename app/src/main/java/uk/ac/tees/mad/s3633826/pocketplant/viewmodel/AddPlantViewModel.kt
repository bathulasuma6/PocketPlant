package uk.ac.tees.mad.s3633826.pocketplant.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uk.ac.tees.mad.s3633826.pocketplant.data.PlantsRepository

/**
 * Holds the state of the Add Plant form.
 *
 * - [commonName] and [nickname] are the text field values.
 * - [canSave] is true only when both fields have non-blank text, used to enable/disable
 *   the Save button.
 * - [savedEvents] emits once after a successful save so the screen can pop back to the
 *   My Plants list.
 */
class AddPlantViewModel(
    private val repository: PlantsRepository,
    private val clock: () -> Long = System::currentTimeMillis,
) : ViewModel() {

    private val _commonName = MutableStateFlow("")
    val commonName: StateFlow<String> = _commonName.asStateFlow()

    private val _nickname = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname.asStateFlow()

    val canSave: StateFlow<Boolean> =
        combine(_commonName, _nickname) { common, nick ->
            common.isNotBlank() && nick.isNotBlank()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false,
        )

    private val _savedEvents = Channel<Unit>(capacity = 1)
    val savedEvents = _savedEvents.receiveAsFlow()

    fun onCommonNameChange(value: String) {
        _commonName.value = value
    }

    fun onNicknameChange(value: String) {
        _nickname.value = value
    }

    fun onSave() {
        if (!canSave.value) return
        viewModelScope.launch {
            repository.addByHand(
                commonName = _commonName.value,
                nickname = _nickname.value,
                now = clock(),
            )
            _commonName.value = ""
            _nickname.value = ""
            _savedEvents.trySend(Unit)
        }
    }

    class Factory(private val repository: PlantsRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            AddPlantViewModel(repository) as T
    }
}
