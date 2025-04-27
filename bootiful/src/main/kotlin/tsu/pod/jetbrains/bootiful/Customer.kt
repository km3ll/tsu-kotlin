package tsu.pod.jetbrains.bootiful

import org.springframework.data.annotation.Id

data class Customer(@Id val id: Int?, val name: String)