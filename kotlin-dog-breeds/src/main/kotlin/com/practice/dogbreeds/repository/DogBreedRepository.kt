package com.practice.dogbreeds.repository

import com.practice.dogbreeds.model.DogBreed
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DogBreedRepository : CoroutineCrudRepository<DogBreed, String> {
    // language=SQL
    @Query(
        """
        SELECT * FROM DOG_BREED
        WHERE SUB_BREED = ''
        """,
    )
    fun findBreedsWithoutSubBreeds(): Flow<DogBreed>

    // language=SQL
    @Query(
        """
        SELECT * FROM DOG_BREED
        WHERE SUB_BREED != ''
        """,
    )
    fun findBreedsWithSubBreeds(): Flow<DogBreed>

    fun findAllByBreed(breed: String): Flow<DogBreed>
}
