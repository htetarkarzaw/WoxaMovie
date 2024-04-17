package com.htetarkarzaw.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.htetarkarzaw.common.Constant
import com.htetarkarzaw.data.local.entity.NowShowingEntity

@Dao
interface NowShowingDao {

    @Upsert
    suspend fun upsertAll(movies: List<NowShowingEntity>)

    @Query("SELECT * FROM ${Constant.NOW_SHOWING_TABLE}")
    fun pagingSource(): PagingSource<Int, NowShowingEntity>

    @Query("DELETE FROM ${Constant.NOW_SHOWING_TABLE}")
    suspend fun deleteAll()
}

