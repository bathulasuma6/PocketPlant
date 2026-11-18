package uk.ac.tees.mad.s3633826.pocketplant.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3633826.pocketplant.R

/**
 * Sprint 1 placeholder for the home screen the user lands on after the splash.
 *
 * Shows a TopAppBar titled "My Plants" and an empty-state message. The real scrolling
 * list of plants, the add-plant FAB and the swipe-to-delete behaviour all arrive in
 * Sprint 2 on their own feature branches.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlantsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.my_plants_title)) },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
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
}
