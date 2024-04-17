package com.htetarkarzaw.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.htetarkarzaw.data.local.entity.DetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: DetailEntity)

    @Query("SELECT * FROM ${DetailEntity.NAME} WHERE id =:id")
    fun getDetail(id: Int): DetailEntity

    @Query("SELECT * FROM ${DetailEntity.NAME} WHERE id =:id")
    fun listenDetail(id: Int): Flow<DetailEntity>

    @Update(entity = DetailEntity::class)
    suspend fun updateDetail(detail: DetailEntity)

    @Query("DELETE FROM ${DetailEntity.NAME}")
    suspend fun deleteAll()

    @Delete(entity = DetailEntity::class)
    suspend fun deleteDetail(detail: DetailEntity)
}