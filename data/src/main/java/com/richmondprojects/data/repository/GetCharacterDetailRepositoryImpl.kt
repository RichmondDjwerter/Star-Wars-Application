package com.richmondprojects.data.repository

import com.richmondprojects.data.network.SwApi
import com.richmondprojects.data.network.utils.SafeApiRequest
import com.richmondprojects.domain.model.Results
import com.richmondprojects.domain.repository.GetCharacterDetailsRepository
import javax.inject.Inject

class GetCharacterDetailRepositoryImpl @Inject constructor(
    private val api: SwApi
) : GetCharacterDetailsRepository, SafeApiRequest() {
    override suspend fun getCharacterDetails(number: String): Results {

        val response = safeApiRequest { api.getCharacterInfo(number) }

        val result = Results(
            birth_year = response.birth_year ?: "",
            created = response.created ?: "",
            url = response.url ?: "",
            name = response.name ?: "",
            height = response.height ?: "",
            mass = response.mass ?: "",
            skin_color = response.skin_color ?: "",
            eye_color = response.eye_color ?: "",
            hair_color = response.hair_color ?: "",
            gender = response.gender ?: "",
            edited = response.edited ?: "",
            homeworld = response.homeworld ?: ""
        )

        return result
    }
}