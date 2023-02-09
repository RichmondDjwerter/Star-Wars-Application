package com.richmondprojects.data.network.di

import android.content.Context
import com.richmondprojects.data.network.SwApi
import com.richmondprojects.data.repository.GetCharacterDetailRepositoryImpl
import com.richmondprojects.data.repository.PagedRepositoryImpl
import com.richmondprojects.data.repository.RepositoryImpl
import com.richmondprojects.data.room.ResultsDao
import com.richmondprojects.data.room.StarDatabase
import com.richmondprojects.domain.repository.GetCharacterDetailsRepository
import com.richmondprojects.domain.repository.PagerRepository
import com.richmondprojects.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://swapi.dev/api/")
            .build()
    }

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): SwApi {
        return retrofit.create(SwApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRepository(api: SwApi): Repository {
        return RepositoryImpl(api = api)
    }

    @Singleton
    @Provides
    fun providePagedRepository(api: SwApi): PagerRepository {
        return PagedRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StarDatabase {
        return StarDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDao(starDatabase: StarDatabase): ResultsDao {
        return starDatabase.getResultDao()
    }

    @Singleton
    @Provides
    fun provideDetailRepository(api: SwApi): GetCharacterDetailsRepository {
        return GetCharacterDetailRepositoryImpl(api)
    }
}