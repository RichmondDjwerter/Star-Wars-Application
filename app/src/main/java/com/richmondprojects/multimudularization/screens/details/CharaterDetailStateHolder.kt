package com.richmondprojects.multimudularization.screens.details

import com.richmondprojects.domain.model.Results

data class CharaterDetailStateHolder(
    val isLoading: Boolean = false,
    val data: Results? = null,
    val error: String = ""
)
