package com.example.freetogame

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.freetogame.ui.GameDetailScreen
import com.example.freetogame.ui.GameEditDetailScreen
import com.example.freetogame.ui.GameListScreen

/**
 * @author Rigoberto Torres on 01/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "gameList") {
        composable("gameList") { GameListScreen(navController = navController) }
        composable("gameDetail/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")
            GameDetailScreen(gameId = gameId!!,navController = navController)
        }
        composable("gameEditDetail/{gameId}") { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")
            GameEditDetailScreen(gameId = gameId!!,navController = navController)
        }
    }
}
