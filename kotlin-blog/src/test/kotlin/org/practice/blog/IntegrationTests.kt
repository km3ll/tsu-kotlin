package org.practice.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.practice.blog.utils.toSlug
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
class IntegrationTests(
    @Autowired val restTemplate: TestRestTemplate,
) {
    @BeforeAll
    fun beforeAll() {
        println(">> beforeAll")
    }

    @AfterAll
    fun afterAll() {
        println(">> afterAll")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        // When
        println(">> Assert blog page title, content and status code")
        val entity = restTemplate.getForEntity<String>("/")
        // Then
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>", "Lorem")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        // When
        println(">> Assert article page title, content and status code")
        val title = "Lorem"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
        // Then
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(title, "Lorem", "dolor sit amet")
    }
}
