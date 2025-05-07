package com.practice.dogbreeds.service

import com.practice.dogbreeds.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface DogBreedService {
    suspend fun save(breeds: Map<String, List<String>>)

    suspend fun getAllBreeds(): Flow<DogBreed>

    suspend fun getUniqueBreeds(): Flow<String>

    suspend fun getAllSubBreeds(): Flow<String>

    suspend fun getSubBreedsByBreed(breed: String): Flow<String>

    suspend fun getImageByBreed(breed: String): Flow<ByteArray>
}
