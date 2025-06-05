package com.practice.dogbreeds.client

data class GetDogBreedsResponse(
    val message: Map<String, List<String>>,
)

data class GetDogBreedImageUrlsResponse(
    val message: List<String>,
)
