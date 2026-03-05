package com.example.lr_11_jetcom.data.repository

import com.example.lr_11_jetcom.data.model.Post
import com.example.lr_11_jetcom.data.remote.PostApi

class PostRepositoryImpl(
    private val api: PostApi // Зависим от интерфейса API
) : PostRepository {
    override suspend fun getPosts(): List<Post> {
        return api.getPosts()
    }
}