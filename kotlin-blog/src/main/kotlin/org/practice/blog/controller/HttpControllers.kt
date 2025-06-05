package org.practice.blog.controller

import org.practice.blog.repository.ArticleRepository
import org.practice.blog.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/article")
class ArticleController(
    private val repository: ArticleRepository,
) {
    @GetMapping("/")
    fun findAll() = repository.findAllByOrderByAddedAtDesc()

    @GetMapping("/{slug}")
    fun findBySlug(
        @PathVariable slug: String,
    ) = repository
        .findBySlug(slug)
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Article with slug '$slug' does not exist")
}

@RestController
@RequestMapping("/api/user")
class UserController(
    private val repository: UserRepository,
) {
    @GetMapping("/")
    fun findAll() = repository.findAll()

    @GetMapping("/{login}")
    fun findByLogin(
        @PathVariable login: String,
    ) = repository
        .findByLogin(login)
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User with login '$login' does not exist")
}
