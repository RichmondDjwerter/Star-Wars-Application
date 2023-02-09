package com.richmondprojects.data.repository

import com.richmondprojects.data.mappers.toDomain
import com.richmondprojects.data.network.SwApi
import com.richmondprojects.data.network.utils.SafeApiRequest
import com.richmondprojects.domain.model.Results
import com.richmondprojects.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: SwApi
) : Repository, SafeApiRequest() {
    override suspend fun getCharacters(page: Int): List<Results> {
        val response = safeApiRequest { api.getCharacters(page) }
        return response.results?.toDomain() ?: emptyList()
    }
}