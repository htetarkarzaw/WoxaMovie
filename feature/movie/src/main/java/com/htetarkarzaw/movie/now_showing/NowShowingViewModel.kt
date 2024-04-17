package com.htetarkarzaw.movie.now_showing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.htetarkarzaw.domain.usecase.GetNowShowing
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NowShowingViewModel @Inject constructor(
    getNowShowing: GetNowShowing,
) : ViewModel() {
    val movies = getNowShowing().cachedIn(viewModelScope)

}