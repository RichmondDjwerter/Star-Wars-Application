package com.richmondprojects.domain.use_cases

import com.richmondprojects.common.Resource
import com.richmondprojects.domain.model.Results
import com.richmondprojects.domain.repository.GetCharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val getCharacterDetailsRepository: GetCharacterDetailsRepository
) {
    operator fun invoke(number: String): Flow<Resource<Results>> = flow {
        emit(Resource.Loading(""))
        try {
            val response = getCharacterDetailsRepository.getCharacterDetails(number)
            emit(Resource.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}