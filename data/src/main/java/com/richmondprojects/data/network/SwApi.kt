package com.richmondprojects.data.network

import com.richmondprojects.data.network.model.CharactersDto
import com.richmondprojects.data.network.model.ResultsDto
import com.richmondprojects.domain.model.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SwApi {
    @GET("people")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharactersDto>

    @GET("people")
    suspend fun getPagination(
        @Query("page") page: Int
    ): Response<CharactersDto>

    @GET("people/{number}")
    suspend fun getCharacterInfo(
        @Path("number") number: String
    ): Response<ResultsDto>
}