package com.practice.dogbreeds.controller

data class DogBreedDto(
    val breed: String,
    val subBreeds: List<String>?,
)

data class GetAllBreedsResponse(
    val breeds: List<DogBreedDto>,
)

data class GetUniqueBreedsResponse(
    val uniqueBreeds: List<String>,
)

data class GetAllSubBreedsResponse(
    val subBreeds: List<String>,
)

data class GetSubBreedsByBreedResponse(
    val subBreeds: List<String>,
)

data class ErrorResponse(
    val message: String,
    val path: String,
)
