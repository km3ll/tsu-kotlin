package tsu.pod.jetbrains.bootiful

import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface CustomerRepository : CoroutineCrudRepository<Customer, Int>