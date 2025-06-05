package com.practice.dogbreeds.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("dog_breed")
data class DogBreed(
    @Id val id: Long?,
    val breed: String,
    val subBreed: String,
    val image: ByteArray,
)
