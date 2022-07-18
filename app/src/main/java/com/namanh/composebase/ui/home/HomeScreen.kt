package com.namanh.composebase.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.namanh.composebase.data.model.Post
import com.namanh.composebase.data.repository.Result
import com.namanh.composebase.utils.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val posts by viewModel.uiState.collectAsStateWithLifecycle()

    when (posts) {
        is Result.Loading -> Loading()
        is Result.Success -> HomeContent(
            posts = (posts as Result.Success<List<Post>>).data,
            modifier = modifier
        )
        is Result.Error -> Text("Error")
    }

}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun HomeContent(posts: List<Post>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(posts) { post ->
            PostItem(post)
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Row(modifier = Modifier.padding(vertical = 10.dp)) {
        AsyncImage(
            model = post.urlToImage,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                text = post.title ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = post.description ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
fun PostItemPreview() {
    val fakeImage = "https://i.pinimg.com/736x/07/93/d0/0793d066fa0ceae94bc89f526153f308.jpg"
    val lipsum =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

    PostItem(
        post = Post(
            id = 0,
            author = "Conan",
            title = "First news",
            description = lipsum,
            url = fakeImage,
            urlToImage = fakeImage,
            publishedAt = "01-01-2022",
            content = ""
        )
    )
}