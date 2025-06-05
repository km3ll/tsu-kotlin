package org.practice.blog.controller

import org.practice.blog.config.BlogProperties
import org.practice.blog.repository.ArticleRepository
import org.practice.blog.utils.render
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(
    private val repository: ArticleRepository,
    private val properties: BlogProperties,
) {
    @GetMapping("/")
    fun blog(model: Model): String {
        // Same as: model.addAttribute("title", "Blog")
        model["title"] = properties.title
        model["banner"] = properties.banner
        model["articles"] =
            repository
                .findAllByOrderByAddedAtDesc()
                .map { it.render() }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(
        @PathVariable slug: String,
        model: Model,
    ): String {
        val article =
            repository
                .findBySlug(slug)
                ?.render()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Article with slug '$slug' does not exist")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }
}
