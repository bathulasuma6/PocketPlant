package uk.ac.tees.mad.s3633826.pocketplant.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3633826.pocketplant.R
import uk.ac.tees.mad.s3633826.pocketplant.data.Plant
import uk.ac.tees.mad.s3633826.pocketplant.viewmodel.MyPlantsViewModel.DeleteEvent

/**
 * Sprint 2 home screen with the full US-04 + US-05 + US-06 behaviour.
 *
 * - Shows saved plants as a scrolling list, or the empty state.
 * - The + FAB opens the Add Plant screen (US-05).
 * - Swiping a row from right to left deletes it (US-06).
 * - A Snackbar with an Undo action appears after deletion. Tapping Undo restores the
 *   plant. Letting the Snackbar time out confirms the delete.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlantsScreen(
    plants: List<Plant>,
    deleteEvent: DeleteEvent?,
    onAddClick: () -> Unit,
    onDelete: (Long) -> Unit,
    onUndoDelete: () -> Unit,
    acknowledgeDelete: (Long) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val deletedLabel = deleteEvent?.plant?.nickname
    val undoLabel = stringResource(R.string.my_plants_deleted_undo)
    val snackbarText = deletedLabel?.let {
        stringResource(R.string.my_plants_deleted_snackbar, it)
    }

    LaunchedEffect(deleteEvent?.ackId) {
        val event = deleteEvent ?: return@LaunchedEffect
        val text = snackbarText ?: return@LaunchedEffect
        val result = snackbarHostState.showSnackbar(
            message = text,
            actionLabel = undoLabel,
            withDismissAction = false,
        )
        when (result) {
            SnackbarResult.ActionPerformed -> onUndoDelete()
            SnackbarResult.Dismissed -> acknowledgeDelete(event.ackId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.my_plants_title)) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.my_plants_add_cd),
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        if (plants.isEmpty()) {
            EmptyState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items = plants, key = { it.id }) { plant ->
                    SwipeablePlantRow(
                        plant = plant,
                        onDelete = { onDelete(plant.id) },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SwipeablePlantRow(
    plant: Plant,
    onDelete: () -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDelete()
                true
            } else {
                false
            }
        },
    )

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        backgroundContent = { SwipeBackground() },
    ) {
        PlantRow(plant = plant)
    }
}

@Composable
private fun SwipeBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onErrorContainer,
        )
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.my_plants_empty_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(R.string.my_plants_empty_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun PlantRow(plant: Plant) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = plant.nickname,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = plant.commonName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
