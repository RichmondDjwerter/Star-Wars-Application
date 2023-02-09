package com.richmondprojects.multimudularization.screens.home

import com.richmondprojects.domain.model.Results

data class HomeState(
    var isLoading: Boolean = false,
    var data: List<Results>? = null,
    var error: String = ""
)
