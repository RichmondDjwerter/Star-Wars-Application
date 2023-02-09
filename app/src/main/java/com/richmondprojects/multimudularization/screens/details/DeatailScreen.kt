package com.richmondprojects.multimudularization.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.richmondprojects.multimudularization.screens.home.ListContentItem

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val res = detailViewModel.details.value

    if (res.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    if (res.error.isNotBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = res.error.toString())
        }
    }
    res.data?.let {
        ListContentItem(it = it, l = {})
    }
}