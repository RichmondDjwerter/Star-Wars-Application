package com.richmondprojects.domain.repository

import com.richmondprojects.common.Resource
import com.richmondprojects.domain.model.Results

interface PagerRepository {

    suspend fun getPagedResults(
        page: Int
    ): Resource<List<Results>>
}