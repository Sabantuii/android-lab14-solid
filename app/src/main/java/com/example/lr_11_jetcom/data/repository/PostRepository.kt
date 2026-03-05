package com.example.lr_11_jetcom.data.repository

import com.example.lr_11_jetcom.data.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}
