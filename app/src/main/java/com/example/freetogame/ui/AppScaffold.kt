package com.example.freetogame.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.freetogame.R
import com.example.freetogame.ui.theme.BackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    title: String = "",
    showSearch: Boolean = false,
    searchQuery: String = "",
    onSearchQueryChanged: (String) -> Unit = {},
    onNavigationIconClick: (() -> Unit)? = null,
    primaryFloatingActionButton: @Composable (() -> Unit)? = null,
    secondaryFloatingActionButton: @Composable (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (showSearch) {
                        TextField(
                            value = searchQuery,
                            onValueChange = onSearchQueryChanged,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(stringResource(R.string.top_bar_serach)) },
                            singleLine = true
                        )
                    } else {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                },
                navigationIcon = {
                    if (onNavigationIconClick != null) {
                        if (showSearch) {
                            IconButton(onClick = onNavigationIconClick) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = stringResource(R.string.top_bar_search),
                                    tint = Color.White
                                )
                            }
                        } else {

                            IconButton(onClick = onNavigationIconClick) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = stringResource(R.string.top_bar_back),
                                    tint = Color.White
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundColor,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = BackgroundColor,
        content = { innerPadding ->
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.padding(16.dp)
            ) {
                content(innerPadding)
                if (primaryFloatingActionButton != null || secondaryFloatingActionButton != null) {
                    TwoFABsLayout(
                        primaryFAB = primaryFloatingActionButton,
                        secondaryFAB = secondaryFloatingActionButton
                    )
                }
            }
        }
    )
}


@Composable
fun TwoFABsLayout(
    primaryFAB: @Composable (() -> Unit)?,
    secondaryFAB: @Composable (() -> Unit)?
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.padding(16.dp)
    ) {
        if (secondaryFAB != null) {
            secondaryFAB()
        }
        if (primaryFAB != null) {
            primaryFAB()
        }
    }
}
