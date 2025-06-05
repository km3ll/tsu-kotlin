package com.practice.dogbreeds.utils

import com.practice.dogbreeds.client.DogBreedApiClient
import com.practice.dogbreeds.service.DogBreedService
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DatabasePopulator(
    private val dogBreedApiClient: DogBreedApiClient,
    private val dogBreedService: DogBreedService,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        runBlocking {
            if (dogBreedService.getAllBreeds().toList().isEmpty()) {
                val breeds = dogBreedApiClient.getBreeds()
                dogBreedService.save(breeds)
            }
        }
    }
}
