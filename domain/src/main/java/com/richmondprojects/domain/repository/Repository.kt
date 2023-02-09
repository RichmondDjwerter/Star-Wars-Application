package com.richmondprojects.domain.repository

import com.richmondprojects.domain.model.Results

interface Repository {

    suspend fun getCharacters(page: Int): List<Results>

}