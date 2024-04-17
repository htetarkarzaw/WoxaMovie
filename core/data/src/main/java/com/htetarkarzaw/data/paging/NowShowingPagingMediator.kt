package com.htetarkarzaw.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.htetarkarzaw.data.local.entity.NowShowingEntity
import com.htetarkarzaw.data.mapper.toNowShowingEntity
import com.htetarkarzaw.data.remote.MovieApiService
import com.kyawlinnthant.codigo.one.data.database.db.MovieDatabase
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class NowShowingPagingMediator @Inject constructor(
    private val apiService: MovieApiService,
    private val database: MovieDatabase,
) : RemoteMediator<Int, NowShowingEntity>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, NowShowingEntity>
    ): MediatorResult {

        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        lastItem.page + 1
                    }
                }
            }
            val response =
                apiService.fetchNowShowingMovies(page = loadKey, size = state.config.pageSize)


            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.nowShowingDao().deleteAll()
                }
                val entities = response.results.map {
                    it.toNowShowingEntity(page = response.page)
                }
                database.nowShowingDao().upsertAll(entities)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.results.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }
}