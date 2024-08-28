package com.example.kotlinspringboot3jwt.service

import com.example.kotlinspringboot3jwt.model.Article
import com.example.kotlinspringboot3jwt.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleRepository) {
    fun findAll(): List<Article> =
        articleRepository.findAll()
}