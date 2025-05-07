package com.practice.dogbreeds

import com.practice.dogbreeds.controller.GetAllBreedsResponse
import com.practice.dogbreeds.controller.GetAllSubBreedsResponse
import com.practice.dogbreeds.controller.GetSubBreedsByBreedResponse
import com.practice.dogbreeds.controller.GetUniqueBreedsResponse
import com.practice.dogbreeds.service.DogBreedService
import io.mockk.clearMocks
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@Tag("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    private val serviceMock = mockk<DogBreedService>()

    @BeforeEach
    fun beforeEach() {
        clearMocks(serviceMock)
        // coEvery { userServiceMock.getUserById(1L) } returns User(1L, "Alice", "alice@example.com")
    }

    @Nested
    inner class GetBreeds {
        @Test
        fun `should return a list of breeds`() {
            val response: GetAllBreedsResponse? =
                webTestClient
                    .get()
                    .uri("/api/v1/breeds")
                    .exchange()
                    .expectStatus()
                    .isOk
                    .expectBody(GetAllBreedsResponse::class.java)
                    .returnResult()
                    .responseBody

            assertNotNull(response)
            response?.let {
                assertFalse(it.breeds.isEmpty())
            }
        }
    }

    @Nested
    inner class GetUniqueBreeds {
        @Test
        fun `should return a list of sub-breeds`() {
            val response: GetUniqueBreedsResponse? =
                webTestClient
                    .get()
                    .uri("/api/v1/breeds/unique")
                    .exchange()
                    .expectStatus()
                    .isOk
                    .expectBody(GetUniqueBreedsResponse::class.java)
                    .returnResult()
                    .responseBody

            assertNotNull(response)
            response?.let {
                assertFalse(it.uniqueBreeds.isEmpty())
            }
        }
    }

    @Nested
    inner class GetImageByBreed {
        @Test
        fun `should return bad request upon empty breed`() {
            webTestClient
                .get()
                .uri("/api/v1/images/ ")
                .exchange()
                .expectStatus()
                .isBadRequest
        }

        @Test
        fun `should return not found upon invalid breed`() {
            webTestClient
                .get()
                .uri("/api/v1/images/unknown")
                .exchange()
                .expectStatus()
                .isNotFound
        }

        @Test
        fun `should return an image upon a valid breed`() {
            val response: ByteArray? =
                webTestClient
                    .get()
                    .uri("/api/v1/images/akita")
                    .exchange()
                    .expectStatus()
                    .isOk
                    .expectBody(ByteArray::class.java)
                    .returnResult()
                    .responseBody

            assertNotNull(response)
            response?.let {
                assertFalse(it.isEmpty())
            }
        }
    }

    @Nested
    inner class GetSubBreeds {
        @Test
        fun `should return a list of breeds`() {
            val response: GetAllSubBreedsResponse? =
                webTestClient
                    .get()
                    .uri("/api/v1/sub-breeds")
                    .exchange()
                    .expectStatus()
                    .isOk
                    .expectBody(GetAllSubBreedsResponse::class.java)
                    .returnResult()
                    .responseBody

            assertNotNull(response)
            response?.let {
                assertFalse(it.subBreeds.isEmpty())
            }
        }
    }

    @Nested
    inner class GetSubBreedsOfBreed {
        @Test
        fun `should return bad request upon empty breed`() {
            webTestClient
                .get()
                .uri("/api/v1/sub-breeds/ ")
                .exchange()
                .expectStatus()
                .isBadRequest
        }

        @Test
        fun `should return not found upon a breed without sub-breeds`() {
            webTestClient
                .get()
                .uri("/api/v1/sub-breeds/unknown")
                .exchange()
                .expectStatus()
                .isNotFound
        }

        @Test
        fun `should return a non-empty list of a breed with sub-breeds`() {
            val response: GetSubBreedsByBreedResponse? =
                webTestClient
                    .get()
                    .uri("/api/v1/sub-breeds/mastiff")
                    .exchange()
                    .expectStatus()
                    .isOk
                    .expectBody(GetSubBreedsByBreedResponse::class.java)
                    .returnResult()
                    .responseBody

            assertNotNull(response)
            response?.let {
                assertFalse(it.subBreeds.isEmpty())
            }
        }
    }
}
