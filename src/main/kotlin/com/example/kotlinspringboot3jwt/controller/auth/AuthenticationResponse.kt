package com.example.kotlinspringboot3jwt.controller.auth

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String,
)
