package com.htetarkarzaw.movie.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.htetarkarzaw.domain.usecase.GetUpcoming
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    getUpcoming: GetUpcoming,
) : ViewModel() {
    val movies = getUpcoming().cachedIn(viewModelScope)
}