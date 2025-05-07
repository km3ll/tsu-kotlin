package com.practice.dogbreeds.service

import com.practice.dogbreeds.client.DogBreedApiClient
import com.practice.dogbreeds.model.DogBreed
import com.practice.dogbreeds.repository.DogBreedRepository
import com.practice.dogbreeds.utils.DogBreedCache
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("unit")
class DogBreedServiceTests {
    private val repository = mockk<DogBreedRepository>(relaxed = true)
    private val client = mockk<DogBreedApiClient>()

    @BeforeEach
    fun beforeEach() {
        clearMocks(repository, client)
    }

    @Nested
    inner class Save {
        @Test
        fun `should store records into repository`() =
            runBlocking {
                // Given
                val records =
                    mapOf<String, List<String>>(
                        "bulldog" to listOf("boston", "english", "french"),
                        "cattledog" to listOf("australian"),
                    )
                val cache = DogBreedCache(repository)
                val service = DogBreedServiceImpl(repository, client, cache)
                // When
                service.save(records)
                // Then
                verify(exactly = 1) { repository.saveAll(any<List<DogBreed>>()) }
            }
    }

    @Nested
    inner class GetAllBreeds {
        @Test
        fun `should return a list of breeds`() =
            runBlocking {
                // Given
                coEvery { repository.findAll() } returns
                    flowOf(
                        DogBreed(id = 1100L, breed = "bakharwal", subBreed = "indian", byteArrayOf()),
                        DogBreed(id = 1101L, breed = "clumber", subBreed = "", byteArrayOf()),
                    )
                val cache = DogBreedCache(repository)
                val service = DogBreedServiceImpl(repository, client, cache)
                // When
                val result = service.getAllBreeds().toList()
                // Then
                assertEquals(2, result.size)
                assertNotNull(result.find { it.breed == "bakharwal" })
                assertNotNull(result.find { it.breed == "clumber" })
            }
    }

    @Nested
    inner class GetUniqueBreeds {
        @Test
        fun `should return a list of breeds with no sub-breeds`() =
            runBlocking {
                // Given
                coEvery { repository.findBreedsWithoutSubBreeds() } returns
                    flowOf(
                        DogBreed(id = 1100L, breed = "bakharwal", subBreed = "", byteArrayOf()),
                        DogBreed(id = 1101L, breed = "clumber", subBreed = "", byteArrayOf()),
                    )
                val cache = DogBreedCache(repository)
                val service = DogBreedServiceImpl(repository, client, cache)
                // When
                val result = service.getUniqueBreeds().toList()
                // Then
                assertEquals(2, result.size)
                assertTrue(result.contains("bakharwal"))
                assertTrue(result.contains("clumber"))
            }
    }

    @Nested
    inner class GetAllSubBreeds {
        @Test
        fun `should return a list of unique sub-breeds`() =
            runBlocking {
                // Given
                coEvery { repository.findBreedsWithSubBreeds() } returns
                    flowOf(
                        DogBreed(id = 1100L, breed = "corgi", subBreed = "cardigan", byteArrayOf()),
                        DogBreed(id = 1101L, breed = "mountain", subBreed = "bernese,swiss", byteArrayOf()),
                        DogBreed(id = 1102L, breed = "hound", subBreed = "afghan,basset,blood,english", byteArrayOf()),
                    )
                val cache = DogBreedCache(repository)
                val service = DogBreedServiceImpl(repository, client, cache)
                // When
                val result = service.getAllSubBreeds().toList()
                // Then
                assertEquals(7, result.size)
                assertTrue(result.contains("swiss"))
                assertTrue(result.contains("basset"))
                assertTrue(result.contains("english"))
            }
    }

    @Nested
    inner class GetSubBreedsByBreed {
        @Test
        fun `should return a list of sub-breeds of a specific breed`() =
            runBlocking {
                // Given
                coEvery { repository.findAllByBreed("mountain") } returns
                    flowOf(
                        DogBreed(id = 1100L, breed = "mountain", subBreed = "bernese,swiss", byteArrayOf()),
                    )
                val cache = DogBreedCache(repository)
                val service = DogBreedServiceImpl(repository, client, cache)
                // When
                val result = service.getSubBreedsByBreed("mountain").toList()
                // Then
                assertEquals(2, result.size)
                assertTrue(result.contains("bernese"))
                assertTrue(result.contains("swiss"))
            }
    }

    @Nested
    inner class GetImageByBreed {
        @Test
        fun `should return empty image when breed not found in DB`() =
            runBlocking {
                // Given
                coEvery { repository.findAllByBreed("akita") } returns emptyFlow()
                val cache = DogBreedCache(repository)
                val service = DogBreedServiceImpl(repository, client, cache)
                // When
                val result = service.getImageByBreed("akita").toList()
                // Then
                assertEquals(1, result.size)
                assertTrue(result.any { it.isEmpty() })
            }

        @Test
        fun `should return image from database record`() =
            runBlocking {
                // Given
                coEvery { repository.findAllByBreed("bakharwal") } returns
                    flowOf(
                        DogBreed(id = 1100L, breed = "bakharwal", subBreed = "indian", "bakharwal.jpeg".toByteArray()),
                    )
                val cache = DogBreedCache(repository)
                val service = DogBreedServiceImpl(repository, client, cache)
                // When
                val result = service.getImageByBreed("bakharwal").toList()
                // Then
                assertEquals(1, result.size)
                assertTrue(result.any { it.isNotEmpty() })
            }

        @Test
        fun `should return image from external service`() =
            runBlocking {
                // Given
                // Existing record with empty image
                coEvery { repository.findAllByBreed("dane") } returns
                    flowOf(
                        DogBreed(id = 1100L, breed = "dane", subBreed = "great", byteArrayOf()),
                    )
                // Image from external service
                coEvery { client.getBreedImage("dane") } returns "dane.jpeg".toByteArray()
                val cache = DogBreedCache(repository)
                val service = DogBreedServiceImpl(repository, client, cache)
                // When
                val result = service.getImageByBreed("dane").toList()
                // Then
                assertEquals(1, result.size)
                assertTrue(result.any { it.isNotEmpty() })

                coVerify { repository.save(any()) }
            }

        @Test
        fun `should update image in existing record after get from external service`() =
            runBlocking {
                // Given
                // Existing record with empty image
                coEvery { repository.findAllByBreed("labradoodle") } returns
                    flowOf(
                        DogBreed(id = 1100L, breed = "labradoodle", subBreed = "", byteArrayOf()),
                    )
                // Image from external service
                coEvery { client.getBreedImage("labradoodle") } returns "labradoodle.jpeg".toByteArray()
                val cache = DogBreedCache(repository)
                val service = DogBreedServiceImpl(repository, client, cache)
                // When
                service.getImageByBreed("labradoodle").toList()
                // Then
                coVerify { repository.save(any()) }
            }
    }
}
