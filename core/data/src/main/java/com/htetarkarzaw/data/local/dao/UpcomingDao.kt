package com.htetarkarzaw.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.htetarkarzaw.common.Constant
import com.htetarkarzaw.data.local.entity.UpcomingEntity

@Dao
interface UpcomingDao {

    @Upsert
    suspend fun upsertAll(movies: List<UpcomingEntity>)

    @Query("SELECT * FROM ${Constant.UPCOMING_TABLE}")
    fun pagingSource(): PagingSource<Int, UpcomingEntity>

    @Query("DELETE FROM ${Constant.UPCOMING_TABLE}")
    suspend fun deleteAll()
}

