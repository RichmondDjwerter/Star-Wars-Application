package com.richmondprojects.multimudularization.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.richmondprojects.data.paging.ResultRemoteMediator
import com.richmondprojects.data.room.ResultsDao
import com.richmondprojects.domain.repository.PagerRepository
import com.richmondprojects.domain.use_cases.SetResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val setResultsUseCase: SetResultsUseCase,
    private val pagerRepository: PagerRepository,
    private val resultsDao: ResultsDao
) :
    ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = PagingConfig(10, 5),
        remoteMediator = ResultRemoteMediator(
            pagerRepository = pagerRepository,
            resultDao = resultsDao
        )
    ) {
        resultsDao.getAllCharacters()
    }.flow.cachedIn(viewModelScope)

//    private val _result = mutableStateOf<HomeState>(HomeState())
//    val result: State<HomeState> = _result

//    init {
//        getResults()
//    }

//    fun getResults() {
//        setResultsUseCase().onEach {
//            when (it) {
//                is Resource.Loading -> {
//                    _result.value = HomeState(isLoading = true)
//                }
//                is Resource.Error -> {
//                    _result.value = HomeState(error = it.message.toString())
//                }
//                is Resource.Success -> {
//                    _result.value = HomeState(data = it.data)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}