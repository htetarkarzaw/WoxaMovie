package com.htetarkarzaw.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.htetarkarzaw.data.repo.MovieRepo
import com.htetarkarzaw.domain.mapper.toVo
import com.htetarkarzaw.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNowShowing @Inject constructor(
    private val repo: MovieRepo
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repo.getNowShowing().flow.map { pagingData ->
            pagingData.map {
                it.toVo()
            }
        }
    }
}
