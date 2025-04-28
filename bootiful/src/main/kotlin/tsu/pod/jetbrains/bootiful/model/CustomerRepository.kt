package tsu.pod.jetbrains.bootiful.model

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CustomerRepository : CoroutineCrudRepository<Customer, Int>