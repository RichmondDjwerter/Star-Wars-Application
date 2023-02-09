package com.richmondprojects.domain.di

import com.richmondprojects.domain.repository.Repository
import com.richmondprojects.domain.use_cases.SetResultsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun getResultsUseCase(repository: Repository): SetResultsUseCase {
        return SetResultsUseCase(repository)
    }
}