package com.htetarkarzaw.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.htetarkarzaw.common.Constant

@Entity(
    tableName = Constant.NOW_SHOWING_TABLE
)
data class NowShowingEntity(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val page : Int
) {
    @PrimaryKey(autoGenerate = true)
    var popularId: Int = 0
}
