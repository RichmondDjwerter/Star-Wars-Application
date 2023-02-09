package com.richmondprojects.data.mappers

import com.richmondprojects.data.network.model.ResultsDto
import com.richmondprojects.domain.model.Results

fun List<ResultsDto>.toDomain(): List<Results>{

    return map{
        Results(
            birth_year = it.birth_year?:"",
            created = it.created?:"",
            url = it.url?:"",
            name = it.name?:"",
            height = it.height?:"",
            mass = it.mass?:"",
            skin_color = it.skin_color?:"",
            eye_color = it.eye_color?:"",
            hair_color = it.hair_color?:"",
            gender = it.gender?:"",
            edited = it.edited?:"",
            homeworld = it.homeworld?:"",
        )
    }
}