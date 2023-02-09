package com.richmondprojects.multimudularization.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richmondprojects.common.Resource
import com.richmondprojects.domain.use_cases.GetCharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val details = mutableStateOf(CharaterDetailStateHolder())

    init {
        savedStateHandle.getLiveData<String>("characterUrl").value?.let {
            getCharacterDetails(it)
        }
    }

    fun getCharacterDetails(number: String) {
        getCharacterDetailUseCase(number).onEach {
            when (it) {
                is Resource.Success -> {
                    details.value = CharaterDetailStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    details.value = CharaterDetailStateHolder(error = it.message.toString())
                }
                is Resource.Loading -> {
                    details.value = CharaterDetailStateHolder(true)
                }
            }
        }.launchIn(viewModelScope)
    }
}