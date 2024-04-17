package com.htetarkarzaw.domain.usecase

import com.htetarkarzaw.data.repo.MovieRepo
import com.htetarkarzaw.domain.mapper.toVo
import com.htetarkarzaw.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetail @Inject constructor(
    private val repo: MovieRepo
) {
    suspend operator fun invoke(id: Int): Flow<MovieDetail> {
        return repo.listenDetail(id).map {
            it?.toVo() ?: MovieDetail()
        }
    }
}