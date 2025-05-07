package com.practice.dogbreeds.service

import com.practice.dogbreeds.client.DogBreedApiClient
import com.practice.dogbreeds.model.DogBreed
import com.practice.dogbreeds.repository.DogBreedRepository
import com.practice.dogbreeds.utils.DogBreedCache
import com.practice.dogbreeds.utils.toDogBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class DogBreedServiceImpl(
    val repository: DogBreedRepository,
    val apiClient: DogBreedApiClient,
    val cache: DogBreedCache,
) : DogBreedService {
    override suspend fun save(breeds: Map<String, List<String>>) {
        repository
            .saveAll(breeds.map { it.toDogBreed() })
            .toList()
    }

    override suspend fun getAllBreeds(): Flow<DogBreed> = cache.getAllBreeds().asFlow()

    override suspend fun getUniqueBreeds(): Flow<String> =
        repository
            .findBreedsWithoutSubBreeds()
            .map { it.breed }

    override suspend fun getAllSubBreeds(): Flow<String> =
        repository
            .findBreedsWithSubBreeds()
            .map { it.subBreed.split(",") }
            .flatMapConcat { subBreeds ->
                flow {
                    subBreeds.forEach { emit(it) }
                }
            }

    override suspend fun getSubBreedsByBreed(breed: String): Flow<String> =
        repository
            .findAllByBreed(breed)
            .map { it.subBreed.split(",") }
            .flatMapConcat { subBreeds ->
                flow {
                    subBreeds.forEach { emit(it) }
                }
            }

    override suspend fun getImageByBreed(breed: String): Flow<ByteArray> {
        val records =
            repository
                .findAllByBreed(breed)
                .toList()
        if (records.isEmpty()) {
            return flowOf(byteArrayOf())
        }
        val dogBreed = records.first()
        if (dogBreed.image.isEmpty()) {
            val image = apiClient.getBreedImage(breed)
            repository.save(dogBreed.copy(image = image))
            return flowOf(image)
        } else {
            return flowOf(dogBreed.image)
        }
    }
}
