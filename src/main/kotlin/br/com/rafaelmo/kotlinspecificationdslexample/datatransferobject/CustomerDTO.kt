package br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject

data class CustomerDTO(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val car: CarDTO?
)