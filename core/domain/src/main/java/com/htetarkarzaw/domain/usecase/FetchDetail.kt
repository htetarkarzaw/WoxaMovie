package com.htetarkarzaw.domain.usecase

import com.htetarkarzaw.data.mapper.toEntity
import com.htetarkarzaw.data.remote.DataResult
import com.htetarkarzaw.data.repo.MovieRepo
import javax.inject.Inject

class FetchDetail @Inject constructor(
    private val repo: MovieRepo
) {
    suspend operator fun invoke(id: Int) {
        when (val response = repo.fetchDetail(id)) {
            is DataResult.ErrorEvent -> {

            }

            is DataResult.SuccessEvent -> {
                repo.insertDetail(response.data.toEntity())
            }
        }

    }
}