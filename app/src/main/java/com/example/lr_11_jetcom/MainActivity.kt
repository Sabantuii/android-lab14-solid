package com.example.lr_11_jetcom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.lr_11_jetcom.data.remote.RetrofitClient
import com.example.lr_11_jetcom.data.repository.PostRepository
import com.example.lr_11_jetcom.data.repository.PostRepositoryImpl
import com.example.lr_11_jetcom.ui.screens.PostListScreen
import com.example.lr_11_jetcom.ui.viewmodel.PostListViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {

    // DIP: Создаём репозиторий с конкретной реализацией
    private val repository: PostRepository by lazy {
        PostRepositoryImpl(RetrofitClient.postApi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // DIP: Передаём зависимости через ViewModelFactory
                val viewModel: PostListViewModel = viewModel {
                    PostListViewModel(repository)
                }
                PostListScreen(viewModel = viewModel)
            }
        }
    }
}