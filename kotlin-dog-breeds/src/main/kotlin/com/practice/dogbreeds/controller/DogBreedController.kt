package com.practice.dogbreeds.controller

import com.practice.dogbreeds.service.DogBreedService
import com.practice.dogbreeds.utils.toDogBreedApiDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class DogBreedController(
    val dogBreedService: DogBreedService,
) {
    @Tag(name = "Breeds")
    @Operation(description = "Returns all breeds.")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = [Content(mediaType = "application/json")])
    @GetMapping("/breeds")
    suspend fun getAllBreeds(): ResponseEntity<GetAllBreedsResponse> {
        val breeds =
            dogBreedService
                .getAllBreeds()
                .toList()
                .map { it.toDogBreedApiDto() }
                .sortedBy { it.breed }
        return ResponseEntity.ok().body(GetAllBreedsResponse(breeds))
    }

    @Tag(name = "Breeds")
    @Operation(description = "Returns breeds that do not have sub-breeds.")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = [Content(mediaType = "application/json")])
    @GetMapping("/breeds/unique")
    suspend fun getUniqueBreeds(): ResponseEntity<GetUniqueBreedsResponse> {
        val uniqueBreeds =
            dogBreedService
                .getUniqueBreeds()
                .toList()
                .sorted()
        return ResponseEntity.ok().body(GetUniqueBreedsResponse(uniqueBreeds))
    }

    @Tag(name = "Breeds")
    @Operation(description = "Returns an image of a breed.")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = [Content(mediaType = "image/jpeg")])
    @ApiResponse(responseCode = "400", description = "Provided breed is not valid", content = [Content(mediaType = "application/json")])
    @ApiResponse(
        responseCode = "404",
        description = "Image not found for provided breed",
        content = [Content(mediaType = "application/json")],
    )
    @GetMapping("/images/{breed}")
    suspend fun getImageByBreed(
        @PathVariable("breed") breed: String,
    ): ResponseEntity<ByteArray> {
        if (breed.isBlank()) {
            throw InvalidBreedException("Breed should not be blank. Received value '$breed'")
        }
        val image =
            dogBreedService
                .getImageByBreed(breed)
                .first()
        if (image.isEmpty()) {
            throw BreedNotFoundException("No image found for received breed '$breed'")
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image)
    }

    @Tag(name = "Sub-Breeds")
    @Operation(description = "Returns all sub-breeds.")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = [Content(mediaType = "application/json")])
    @GetMapping("/sub-breeds")
    suspend fun getAllSubBreeds(): ResponseEntity<GetAllSubBreedsResponse> {
        val subBreeds =
            dogBreedService
                .getAllSubBreeds()
                .toList()
                .distinct()
                .sorted()
        return ResponseEntity.ok().body(GetAllSubBreedsResponse(subBreeds))
    }

    @Tag(name = "Sub-Breeds")
    @Operation(description = "Returns sub-breeds of a breed.")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = [Content(mediaType = "application/json")])
    @ApiResponse(responseCode = "400", description = "Provided breed is not valid", content = [Content(mediaType = "application/json")])
    @ApiResponse(
        responseCode = "404",
        description = "Sub-breeds not found for provided breed",
        content = [Content(mediaType = "application/json")],
    )
    @GetMapping("/sub-breeds/{breed}")
    suspend fun getSubBreedsByBreed(
        @PathVariable("breed") breed: String,
    ): ResponseEntity<GetSubBreedsByBreedResponse> {
        if (breed.isBlank()) {
            throw InvalidBreedException("Breed should not be blank. Received value '$breed'")
        }
        val subBreeds =
            dogBreedService
                .getSubBreedsByBreed(breed)
                .toList()
                .sorted()
        if (subBreeds.isEmpty()) {
            throw BreedNotFoundException("No sub-breeds found for received breed '$breed'")
        }
        return ResponseEntity.ok().body(GetSubBreedsByBreedResponse(subBreeds))
    }
}
