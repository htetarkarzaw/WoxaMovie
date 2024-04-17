package com.htetarkarzaw.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.htetarkarzaw.data.local.entity.DetailEntity
import com.htetarkarzaw.data.local.entity.NowShowingEntity
import com.htetarkarzaw.data.local.entity.UpcomingEntity
import com.htetarkarzaw.data.paging.NowShowingPagingMediator
import com.htetarkarzaw.data.paging.UpcomingPagingMediator
import com.htetarkarzaw.data.remote.DataResult
import com.htetarkarzaw.data.remote.MovieApiService
import com.htetarkarzaw.data.remote.dto.detail.MovieDetailDto
import com.htetarkarzaw.data.remote.safeApiCall
import com.kyawlinnthant.codigo.one.data.database.db.MovieDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val api: MovieApiService,
    private val db: MovieDatabase
) : MovieRepo {
    companion object {
        const val PAGE_SIZE = 50
        const val INITIAL_LOAD_SIZE = PAGE_SIZE * 3
        const val START_PAGE = 1
        const val PREFETCH_DISTANCE = 1
        const val MAX_LOAD_SIZE = PagingConfig.MAX_SIZE_UNBOUNDED
    }

    private val nowShowingDao = db.nowShowingDao()
    private val upcomingDao = db.upcomingDao()
    private val detailDao = db.detailDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getNowShowing(): Pager<Int, NowShowingEntity> {
        val dbSource = { nowShowingDao.pagingSource() }
        val config = PagingConfig(
            initialLoadSize = INITIAL_LOAD_SIZE,
            pageSize = PAGE_SIZE,
            maxSize = MAX_LOAD_SIZE,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = PREFETCH_DISTANCE,
        )
        val remoteMediator = NowShowingPagingMediator(
            apiService = api,
            database = db
        )
        return Pager(
            config = config,
            initialKey = START_PAGE,
            remoteMediator = remoteMediator,
            pagingSourceFactory = dbSource,
        )
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getUpcoming(): Pager<Int, UpcomingEntity> {
        val dbSource = { upcomingDao.pagingSource() }
        val config = PagingConfig(
            initialLoadSize = INITIAL_LOAD_SIZE,
            pageSize = PAGE_SIZE,
            maxSize = MAX_LOAD_SIZE,
            jumpThreshold = 1,
            enablePlaceholders = true,
            prefetchDistance = PREFETCH_DISTANCE,
        )
        val remoteMediator = UpcomingPagingMediator(
            apiService = api,
            database = db
        )
        return Pager(
            config = config,
            initialKey = START_PAGE,
            remoteMediator = remoteMediator,
            pagingSourceFactory = dbSource,
        )
    }

    override suspend fun fetchDetail(id: Int): DataResult<MovieDetailDto> {
        return safeApiCall {
            api.getDetail(
                id = id
            )
        }
    }

    override suspend fun insertDetail(detail: DetailEntity) {
        detailDao.insertDetail(detail)
    }

    override suspend fun listenDetail(id: Int): Flow<DetailEntity> {
        return detailDao.listenDetail(id)
    }

}