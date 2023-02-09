package com.richmondprojects.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.richmondprojects.common.Resource
import com.richmondprojects.data.room.ResultRemoteKey
import com.richmondprojects.data.room.ResultsDao
import com.richmondprojects.domain.model.Results
import com.richmondprojects.domain.repository.PagerRepository
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ResultRemoteMediator @Inject constructor(
    private val initialPage: Int = 1,
    private val resultDao: ResultsDao,
    private val pagerRepository: PagerRepository
) : RemoteMediator<Int, Results>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Results>
    ): MediatorResult {
        return try {
            val page: Int = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKey = getLastKey(state)
                    remoteKey?.nextPage ?: return MediatorResult.Success(true)
                }
                LoadType.PREPEND -> {
                   return MediatorResult.Success(true)
                }
                LoadType.REFRESH -> {
                    val remoteKey = getClosestKey(state)
                    remoteKey?.nextPage?.minus(1) ?: initialPage
                }
            }

            val response = pagerRepository.getPagedResults(page)
            val endOfPagination = response.data?.size!! < state.config.pageSize

            when (response) {
                is Resource.Success -> {
                    val body = response.data

                    if (loadType == LoadType.REFRESH) {
                        resultDao.deleteAllKeys()
                        resultDao.getAllCharacters()
                    }
                    val prevPage = if (page == 1) null else page - 1
                    val nextPage = if (endOfPagination) null else page + 1

                    val list = body?.map {
                        ResultRemoteKey(it.name, nextPage, prevPage)
                    }
                    list?.let { resultDao.insertAllKeys(list) }
                    list?.let { resultDao.insertAllCharacters(body) }
                }
                is Resource.Error -> {
                    MediatorResult.Error(Exception())
                }
            }
            if (response is Resource.Success) {
                if (endOfPagination) {
                    MediatorResult.Success(true)
                } else {
                    MediatorResult.Success(false)
                }
            } else {
                MediatorResult.Success(true)
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    suspend fun getLastKey(state: PagingState<Int, Results>): ResultRemoteKey? {
        return state.lastItemOrNull()?.let {
            resultDao.getRemoteKey(it.name)
        }
    }

    suspend fun getClosestKey(state: PagingState<Int, Results>): ResultRemoteKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                resultDao.getRemoteKey(it.name)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Results>): ResultRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {
                resultDao.getRemoteKey(it.name)
            }
    }
}