package com.example.lr_11_jetcom.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lr_11_jetcom.data.model.Post
import com.example.lr_11_jetcom.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Данные для состояния UI
class PostListViewModel(
    private val repository: PostRepository
) : ViewModel() {

    // Данные для состояния UI
    data class PostUiState(
        val posts: List<Post> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState

    // SRP: Метод отвечает только за загрузку данных
    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val posts = repository.getPosts()
                _uiState.value = _uiState.value.copy(posts = posts, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Неизвестная ошибка",
                    isLoading = false
                )
            }
        }
    }
}