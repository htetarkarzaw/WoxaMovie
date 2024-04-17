package com.htetarkarzaw.movie.now_showing


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.htetarkarzaw.domain.model.Movie
import com.htetarkarzaw.movie.R
import com.htetarkarzaw.movie.component.MovieItem

@Composable
fun NowShowingScreen(
    movies: LazyPagingItems<Movie>,
    onAction: (NowShowingAction) -> Unit
) {
    movies.apply {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = movies.itemCount,
                key = movies.itemKey { it.id }
            ) { index ->

                val currentMovie = movies[index] ?: Movie()
                MovieItem(movie = currentMovie) {
                    onAction(NowShowingAction.Navigate(id = currentMovie.id))
                }
            }
            item {
                if (loadState.append is LoadState.Error) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                        TextButton(onClick = { retry() }) {
                            Text(text = stringResource(id = R.string.retry))
                        }
                    }
                }
            }
            item {
                if (loadState.mediator?.refresh is LoadState.Error) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        TextButton(onClick = { retry() }) {
                            Text(text = stringResource(id = R.string.retry))
                        }
                    }
                }
            }
            item {
                if (loadState.append.endOfPaginationReached) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                    }
                    ListItem(headlineContent = { Text(text = "...") })
                }
            }
        }

    }
}

