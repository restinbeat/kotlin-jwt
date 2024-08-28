package com.example.kotlinspringboot3jwt.controller.auth

data class AuthenticationRequest(
    val email: String,
    val password: String,
)
