package com.practice.dogbreeds.client

interface DogBreedApiClient {
    suspend fun getBreeds(): Map<String, List<String>>

    suspend fun getBreedImage(breed: String): ByteArray
}
