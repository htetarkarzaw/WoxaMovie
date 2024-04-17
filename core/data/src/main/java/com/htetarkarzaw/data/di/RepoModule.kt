package com.htetarkarzaw.data.di

import com.htetarkarzaw.data.repo.MovieRepo
import com.htetarkarzaw.data.repo.MovieRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    @Singleton
    fun bindRepo(repo: MovieRepoImpl): MovieRepo
}