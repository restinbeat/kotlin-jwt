package com.example.kotlinspringboot3jwt.controller.article

import java.util.*

data class ArticleResponse(
    val id: UUID,
    val title: String,
    val content: String,
)
