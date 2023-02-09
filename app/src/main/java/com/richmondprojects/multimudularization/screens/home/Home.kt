package com.richmondprojects.multimudularization.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.richmondprojects.domain.model.Results

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

//    val res = viewModel.result.value

//    if (res.isLoading) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            CircularProgressIndicator()
//        }
//    }
//    if (res.error.isNotBlank()) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            Text(
//                text = res.error.toString(),
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    }
    val list = viewModel.pager.collectAsLazyPagingItems()
    LazyColumn {
        items(list.itemCount) {
            ListContentItem(it = list[it]!!) {
                navController.navigate("Details/${it}")
            }
        }
    }
}

@Composable
fun ListContentItem(it: Results, l: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth().padding(6.dp)
        .clickable {
            l.invoke(
                it.url
                    ?.dropLast(1)
                    ?.takeLastWhile { it.isDigit() }
                    .toString()
            )
        }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Character Name: ${it.name}",
                color = MaterialTheme.colors.onBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${it.birth_year}",
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Eye Color: ${it.eye_color}",
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(3.dp))
        Divider()
    }
}
