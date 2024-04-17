package com.kyawlinnthant.codigo.one.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.htetarkarzaw.common.Constant
import com.htetarkarzaw.data.local.dao.DetailDao
import com.htetarkarzaw.data.local.dao.NowShowingDao
import com.htetarkarzaw.data.local.dao.UpcomingDao
import com.htetarkarzaw.data.local.entity.DetailEntity
import com.htetarkarzaw.data.local.entity.NowShowingEntity
import com.htetarkarzaw.data.local.entity.UpcomingEntity

@Database(
    entities = [
        NowShowingEntity::class,
        UpcomingEntity::class,
        DetailEntity::class,

    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun nowShowingDao(): NowShowingDao
    abstract fun upcomingDao(): UpcomingDao
    abstract fun detailDao(): DetailDao

    companion object {
        const val DB_NAME = Constant.MOVIE_DATABASE
    }
}