package com.example.dailypulse.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.dailypulse.source.application.Source
import com.example.dailypulse.source.presentation.SourcesViewModel
import kotlinx.coroutines.coroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun SourcesScreen(
    onUpButtonClick: () -> Unit,
    sourcesViewModel: SourcesViewModel = koinViewModel(),
) {
    val sourcesState = sourcesViewModel.sourcesState.collectAsState()

    Column {
        AppBar(onUpButtonClick)

        if (sourcesState.value.error != null)
            ErrorMessage(sourcesState.value.error!!)
        if (sourcesState.value.sources.isNotEmpty())
            SourcesListView(sourcesViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onUpButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Sources") },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Sources Button",
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SourcesListView(viewModel: SourcesViewModel) {

    val pullRefreshState = rememberPullToRefreshState { true }

    if (pullRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            coroutineScope {
                viewModel.getSources(true) {
                    pullRefreshState.endRefresh()
                }
            }
        }
    }
    Box(Modifier.nestedScroll(pullRefreshState.nestedScrollConnection).zIndex(-1f)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.sourcesState.value.sources) { source ->
                SourceItemView(source = source)
            }
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
        )
    }
}

@Composable
private fun SourceItemView(source: Source) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = source.name,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = source.description)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${source.language} - ${source.country}",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}