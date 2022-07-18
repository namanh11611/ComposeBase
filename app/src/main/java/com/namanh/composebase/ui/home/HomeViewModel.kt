package com.namanh.composebase.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namanh.composebase.data.model.Post
import com.namanh.composebase.data.repository.PostRepository
import com.namanh.composebase.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    postRepo: PostRepository
) : ViewModel() {
    private val _uiState = postRepo.getPostsStream()
        .onStart {
            postRepo.refreshPosts()
            emit(Result.Loading)
        }

    val uiState : StateFlow<Result<List<Post>>> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Result.Loading
    )
}