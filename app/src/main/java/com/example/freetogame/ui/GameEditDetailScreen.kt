package com.example.freetogame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.freetogame.R
import com.example.freetogame.ui.main.viewmodel.GamesDetailEditViewModel
import com.example.freetogame.ui.main.viewmodel.GamesDetailViewModel
import com.example.freetogame.ui.theme.BackgroundColor
import com.example.freetogame.ui.theme.btnColor

/**
 * @author Rigoberto Torres on 04/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */

@Composable
fun GameEditDetailScreen(
    gameId: String,
    viewModel: GamesDetailEditViewModel = hiltViewModel(),
    navController: NavHostController
) {

    LaunchedEffect(gameId) {
        viewModel.getGameById(gameId.toInt())
    }
    val game by viewModel.selectedGame.collectAsState()

    AppScaffold(
        title = stringResource(R.string.game_detail_edit_title),
        onNavigationIconClick = { navController.popBackStack() },
        primaryFloatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.updateGame()
                    navController.popBackStack()
                },
                containerColor = btnColor,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = stringResource(R.string.game_detail_edit_save_btn)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = game.thumbnail),
                contentDescription = game.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = viewModel.title,
                        onValueChange = { viewModel.title = it },
                        label = { Text(stringResource(R.string.game_detail_edit_title_field)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.shortDescription,
                        onValueChange = { viewModel.shortDescription = it },
                        label = { Text(stringResource(R.string.game_detail_short_desctiption_label)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Sección de Detalles Adicionales
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = viewModel.genre,
                        onValueChange = { viewModel.genre = it },
                        label = { Text(stringResource(R.string.game_detail_gnre_label)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.platform,
                        onValueChange = { viewModel.platform = it },
                        label = { Text(stringResource(R.string.game_detail_platform_label)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.publisher,
                        onValueChange = { viewModel.publisher = it },
                        label = { Text(stringResource(R.string.game_detail_publisher_label)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.developer,
                        onValueChange = { viewModel.developer = it },
                        label = { Text(stringResource(R.string.game_detail_developer_label)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = viewModel.releaseDate,
                        onValueChange = { viewModel.releaseDate = it },
                        label = { Text(stringResource(R.string.game_detail_release_date_label)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

