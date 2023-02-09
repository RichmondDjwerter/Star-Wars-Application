package com.richmondprojects.data.repository

import com.richmondprojects.common.Resource
import com.richmondprojects.data.mappers.toDomain
import com.richmondprojects.data.network.SwApi
import com.richmondprojects.domain.model.Results
import com.richmondprojects.domain.repository.PagerRepository
import javax.inject.Inject

class PagedRepositoryImpl @Inject constructor(
    private val api: SwApi
) : PagerRepository {
    override suspend fun getPagedResults(page: Int): Resource<List<Results>> {
        return try {
            val response = api.getPagination(page)
            if (response.isSuccessful) {
                val body = response.body()?.results?.toDomain()
                Resource.Success(body)
            } else {
                Resource.Error(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage)
        }
    }

}