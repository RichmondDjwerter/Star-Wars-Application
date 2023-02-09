package com.richmondprojects.data.network.model

data class CharactersDto(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<ResultsDto>?
)