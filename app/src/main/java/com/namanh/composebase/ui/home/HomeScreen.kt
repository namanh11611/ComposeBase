package com.namanh.composebase.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.namanh.composebase.model.News

const val fakeImage1 = "https://i.pinimg.com/736x/07/93/d0/0793d066fa0ceae94bc89f526153f308.jpg"
const val fakeImage2 = "https://i.pinimg.com/originals/21/92/cb/2192cb75dbba15be24db8021d20d9588.png"
const val lipsum =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."

@Composable
fun HomeScreen(modifier: Modifier) {
    val newsList = mutableListOf<News>()
    newsList.add(News(0, fakeImage1, "First news", lipsum))
    newsList.add(News(1, fakeImage2, "Second news", lipsum))
    newsList.add(News(2, fakeImage1, "Third news", lipsum))
    newsList.add(News(3, fakeImage2, "Fourth news", lipsum))
    newsList.add(News(4, fakeImage1, "Fifth news", lipsum))
    newsList.add(News(5, fakeImage2, "Sixth news", lipsum))
    newsList.add(News(6, fakeImage1, "Seventh news", lipsum))
    newsList.add(News(7, fakeImage2, "Eighth news", lipsum))
    newsList.add(News(8, fakeImage1, "Nine news", lipsum))
    newsList.add(News(9, fakeImage2, "Ten news", lipsum))
    
    LazyColumn(modifier = modifier) {
        items(newsList) { news ->
            NewsItem(news)
        }
    }
}

@Composable
fun NewsItem(news: News) {
    Row(modifier = Modifier.padding(vertical = 10.dp)) {
        AsyncImage(
            model = news.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp)
        )
        Column {
            Text(
                text = news.title,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = news.description,
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
fun NewsItemPreview() {
    NewsItem(news = News(0, fakeImage1, "First news", lipsum))
}