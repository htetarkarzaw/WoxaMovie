package com.htetarkarzaw.movie.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.htetarkarzaw.movie.detail.DetailScreen
import com.htetarkarzaw.movie.detail.DetailViewModel
import com.htetarkarzaw.movie.now_showing.NowShowingAction
import com.htetarkarzaw.movie.now_showing.NowShowingScreen
import com.htetarkarzaw.movie.now_showing.NowShowingViewModel
import com.htetarkarzaw.movie.upcoming.UpcomingAction
import com.htetarkarzaw.movie.upcoming.UpcomingScreen
import com.htetarkarzaw.movie.upcoming.UpcomingViewModel

@Composable
fun MovieGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screen.NowShowing.route,
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        composable(Screen.NowShowing.route) {

            val vm: NowShowingViewModel = hiltViewModel()
            val movies = vm.movies.collectAsLazyPagingItems()
            NowShowingScreen(movies = movies, onAction = { action ->
                when (action) {
                    is NowShowingAction.Navigate -> navController.navigate(Screen.Detail.arg(id = action.id))
                }
            })
        }
        composable(Screen.Upcoming.route) {
            val vm: UpcomingViewModel = hiltViewModel()
            val movies = vm.movies.collectAsLazyPagingItems()
            UpcomingScreen(movies = movies, onAction = { action ->
                when (action) {
                    is UpcomingAction.Navigate -> navController.navigate(Screen.Detail.arg(id = action.id))
                }
            })
        }
        composable(
            route = Screen.Detail.route(),
            arguments = listOf(navArgument(NavArgument.MOVIE_ID) { type = NavType.IntType })
        ) {
            val vm: DetailViewModel = hiltViewModel()
            val movie = vm.movie.collectAsState()
            LaunchedEffect(Unit) {
                val id = it.arguments?.getInt(NavArgument.MOVIE_ID)
                id?.let { movieId ->
                    vm.getMovie(id = movieId)
                    vm.fetchMovies(id = movieId)
                }

            }
            DetailScreen(movie = movie.value,
                onBackClick = navController::popBackStack)
        }
    }
}