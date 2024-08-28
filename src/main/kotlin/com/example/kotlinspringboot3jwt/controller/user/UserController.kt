package com.example.kotlinspringboot3jwt.controller.user

import com.example.kotlinspringboot3jwt.service.UserService
import com.example.kotlinspringboot3jwt.model.Role
import com.example.kotlinspringboot3jwt.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun create(@RequestBody userRequest: UserRequest): UserResponse =
        userService.createUser(userRequest.toModel())
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create user.")

    @GetMapping
    fun listAll(): List<UserResponse> =
        userService.findAll()
            .map { it.toResponse() }

    @GetMapping("/{uuid}")
    fun findByUUID(@PathVariable uuid: UUID): UserResponse =
        userService.findByUUID(uuid)
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")

    @DeleteMapping("/{uuid}")
    fun deleteByUUID(@PathVariable uuid: UUID): ResponseEntity<Boolean> {
        val success = userService.deleteByUUID(uuid)

        return if (success)
            ResponseEntity.noContent()
                .build()
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.")
    }

    private fun User.toResponse(): UserResponse =
        UserResponse(
            uuid = this.id,
            email = this.email,
        )

    private fun UserRequest.toModel(): User =
        User(
            id = UUID.randomUUID(),
            email = this.email,
            password = this.password,
            role = Role.USER,
        )
}
