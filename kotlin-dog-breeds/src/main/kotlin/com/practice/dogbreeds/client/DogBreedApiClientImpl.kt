package com.practice.dogbreeds.client

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import kotlin.collections.emptyMap

@Component
class DogBreedApiClientImpl(
    private val webClient: WebClient,
) : DogBreedApiClient {
    private val dogBreedApiUrl = "https://dog.ceo/api/breeds/list/all"
    private val dogBreedImageApiUrl = "https://dog.ceo/api/breed/%s/images"

    override suspend fun getBreeds(): Map<String, List<String>> =
        webClient
            .get()
            .uri(dogBreedApiUrl)
            .retrieve()
            .bodyToMono(GetDogBreedsResponse::class.java)
            .block()
            ?.message
            ?: emptyMap()

    override suspend fun getBreedImage(breed: String): ByteArray {
        val urls = getImageUrls(breed)
        return getImage(urls.first())
    }

    private fun getImageUrls(breed: String): List<String> =
        webClient
            .get()
            .uri(dogBreedImageApiUrl.format(breed))
            .retrieve()
            .bodyToMono(GetDogBreedImageUrlsResponse::class.java)
            .block()
            ?.message
            ?: emptyList()

    private fun getImage(uri: String): ByteArray {
        val result =
            webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ByteArray::class.java)
                .block()
        return if (result == null) {
            throw RuntimeException("Failed to fetch image from: $uri")
        } else {
            result
        }
    }
}
