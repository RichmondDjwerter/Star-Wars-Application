package com.richmondprojects.domain.use_cases

import com.richmondprojects.common.Resource
import com.richmondprojects.domain.model.Results
import com.richmondprojects.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetResultsUseCase @Inject constructor(
    private val repository: Repository
) {
    var page = 1
    operator fun invoke(): Flow<Resource<List<Results>>> = flow {
        emit(Resource.Loading(null))
        try {
            val response = repository.getCharacters(page)
            emit(Resource.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error("An Error Occurred"))
        }
    }
}