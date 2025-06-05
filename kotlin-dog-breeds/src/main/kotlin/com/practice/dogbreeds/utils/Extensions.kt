package com.practice.dogbreeds.utils

import com.practice.dogbreeds.controller.DogBreedDto
import com.practice.dogbreeds.model.DogBreed

fun DogBreed.toDogBreedApiDto(): DogBreedDto {
    val subBreeds =
        if (subBreed.isNotBlank()) {
            subBreed.split(",").toList()
        } else {
            null
        }
    return DogBreedDto(breed = this.breed, subBreeds = subBreeds)
}

fun Map.Entry<String, List<String>>.toDogBreed(): DogBreed {
    val subBreed = this.value.joinToString(",")
    return DogBreed(id = null, breed = this.key, subBreed = subBreed, image = byteArrayOf())
}
