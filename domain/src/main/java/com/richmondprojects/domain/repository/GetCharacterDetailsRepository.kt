package com.richmondprojects.domain.repository

import com.richmondprojects.domain.model.Results

interface GetCharacterDetailsRepository {

    suspend fun getCharacterDetails(number: String): Results
}