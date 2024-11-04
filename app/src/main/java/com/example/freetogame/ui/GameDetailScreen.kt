package com.example.freetogame.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.freetogame.R
import com.example.freetogame.ui.main.viewmodel.GamesDetailViewModel
import com.example.freetogame.ui.theme.BackgroundColor
import com.example.freetogame.ui.theme.TypeA
import com.example.freetogame.ui.theme.btnColor
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @author Rigoberto Torres on 04/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */

@Composable
fun GameDetailScreen(
    gameId: String,
    viewModel: GamesDetailViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    LaunchedEffect(gameId) {
        viewModel.getGameById(gameId.toInt())
    }
    val game by viewModel.selectedGame.collectAsState()

    AppScaffold(
        title = game.title,
        onNavigationIconClick = { navController.popBackStack() },
        primaryFloatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("gameEditDetail/$gameId")
                },
                containerColor = Color.Blue,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.game_detail_title)
                )
            }
        },
        secondaryFloatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.deleteGame()
                    navController.popBackStack()
                },
                containerColor = Color.Red,
                contentColor = Color.White,
                modifier = Modifier.offset(y = (-70).dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.game_detail_delete_btn)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = game.thumbnail),
                contentDescription = game.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = game.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = game.shortDescription,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                color = TypeA,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            GameDetailItem(
                label = stringResource(R.string.game_detail_gnre_label),
                value = game.genre
            )
            GameDetailItem(
                label = stringResource(R.string.game_detail_platform_label),
                value = game.platform
            )
            GameDetailItem(
                label = stringResource(R.string.game_detail_publisher_label),
                value = game.publisher
            )
            GameDetailItem(
                label = stringResource(R.string.game_detail_developer_label),
                value = game.developer
            )
            GameDetailItem(
                label = stringResource(R.string.game_detail_release_date_label),
                value = game.releaseDate?.let { dateFormat.format(it) }
                    ?: stringResource(R.string.game_detail_diable_label)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(game.gameUrl))
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = btnColor)
            ) {
                Text(stringResource(R.string.game_detail_navigator_label))
            }
        }
    }
}

@Composable
fun GameDetailItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = TypeA,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
        )
    }
}
