package com.example.freetogame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.freetogame.ui.main.viewmodel.ActivityMainViewModel
import com.example.freetogame.ui.theme.MyApplicationTheme
import com.example.freetogame.utils.Constants.Companion.DOWNLOAD_PREFERENCES
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Rigoberto Torres on 01/11/2024.
 * @version 0.0.1
 * @since 0.0.1
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ActivityMainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            !viewModel.isSplashDataLoaded.value
        }

        if (!viewModel.getPreference(DOWNLOAD_PREFERENCES)) {
            viewModel.getAllGames()
        } else {
            viewModel._isSplashDataLoaded.value = true
        }

        setContent {
            MyApplicationTheme {
                LaunchedEffect(Unit) {
                    viewModel.isSplashDataLoaded.collect { isLoaded ->
                        if (isLoaded) {
                            viewModel.savePreference(DOWNLOAD_PREFERENCES, true)
                        }
                    }
                }

                Scaffold(containerColor = MaterialTheme.colorScheme.background,
                    content = { innerPadding ->
                        val navController = rememberNavController()
                        NavigationGraph(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                )
            }
        }
    }
}