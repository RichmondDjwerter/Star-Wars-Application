package com.richmondprojects.multimudularization.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(8.dp))
        SearchBar(
            hint = "Search...", modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn {
            items(list.itemCount) {
                ListContentItem(it = list[it]!!) {
                    navController.navigate("Details/${it}")
                }
            }
        }
    }

}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp)
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun ListContentItem(it: Results, l: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
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
