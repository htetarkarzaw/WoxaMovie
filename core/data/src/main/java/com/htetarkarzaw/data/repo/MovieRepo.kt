package com.htetarkarzaw.data.repo

import androidx.paging.Pager
import com.htetarkarzaw.data.local.entity.DetailEntity
import com.htetarkarzaw.data.local.entity.NowShowingEntity
import com.htetarkarzaw.data.local.entity.UpcomingEntity
import com.htetarkarzaw.data.remote.DataResult
import com.htetarkarzaw.data.remote.dto.detail.MovieDetailDto
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getNowShowing(): Pager<Int, NowShowingEntity>
    fun getUpcoming(): Pager<Int, UpcomingEntity>

    suspend fun fetchDetail(id: Int): DataResult<MovieDetailDto>
    suspend fun insertDetail(detail: DetailEntity)

    suspend fun listenDetail(id: Int): Flow<DetailEntity?>

}