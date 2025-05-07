package com.practice.dogbreeds.utils

import com.practice.dogbreeds.model.DogBreed
import com.practice.dogbreeds.repository.DogBreedRepository
import kotlinx.coroutines.flow.toList
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class DogBreedCache(
    val repository: DogBreedRepository,
) {
    @Cacheable(cacheNames = ["breeds"], unless = "#result.isEmpty()")
    suspend fun getAllBreeds(): List<DogBreed> = repository.findAll().toList()
}
